package com.assignment.rsupport.noticejava.web;

import com.assignment.rsupport.noticejava.domain.notice.Notice;
import com.assignment.rsupport.noticejava.domain.notice.NoticeRepository;
import com.assignment.rsupport.support.test.AcceptanceTest;
import com.assignment.rsupport.support.test.HtmlFormDataBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NoticeAcceptanceTest extends AcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NoticeAcceptanceTest.class);

    @Autowired
    private NoticeRepository noticeRepository;

    private HtmlFormDataBuilder htmlFormDataBuilder;

    @Test
    public void createForm_fail() {
        ResponseEntity<String> responseEntity = template().getForEntity("/notices/form", String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void createForm_success() {
        ResponseEntity<String> responseEntity = basicAuthTemplate().getForEntity("/notices/form", String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        logger.debug("body : {}", responseEntity.getBody());
    }

    @Test
    public void create() {
        htmlFormDataBuilder = HtmlFormDataBuilder.urlEncodedForm()
                .addParameter("title", "test notice")
                .addParameter("content", "this is test");

        ResponseEntity<String> responseEntity = basicAuthTemplate().postForEntity("/notices", htmlFormDataBuilder.build(), String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        Notice notice = noticeRepository.findById(2L).get();
        logger.debug("notice : {}", notice);
        softly.assertThat(noticeRepository.findById(2L).isPresent()).isTrue();
        softly.assertThat(responseEntity.getHeaders().getLocation().getPath()).startsWith("/");
    }

    @Test
    public void show() {
        ResponseEntity<String> responseEntity = template().getForEntity("/notices/1", String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        logger.debug("body : {}", responseEntity.getBody());
    }

    @Test
    public void updateForm_not_match_writer() {
        ResponseEntity<String> responseEntity = basicAuthTemplate(TEST_USER_2).getForEntity("/notices/1/form", String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void updateForm_match_writer() {
        ResponseEntity<String> responseEntity = basicAuthTemplate().getForEntity("/notices/1/form", String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        logger.debug("body : {}", responseEntity.getBody());
    }

    @Test
    public void update() {
        htmlFormDataBuilder = HtmlFormDataBuilder.urlEncodedForm().put()
                .addParameter("title", "update")
                .addParameter("content", "update");

        logger.debug("original : {}", noticeRepository.findById(1L).get());
        ResponseEntity<String> responseEntity = basicAuthTemplate().postForEntity("/notices/1", htmlFormDataBuilder.build(), String.class);
        logger.debug("update : {}", noticeRepository.findById(1L).get());
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        softly.assertThat(responseEntity.getHeaders().getLocation().getPath().startsWith("/notices/1")).isTrue();
    }

    @Test
    public void delete_by_not_owner() {
        htmlFormDataBuilder = HtmlFormDataBuilder.urlEncodedForm().delete();
        ResponseEntity<String> responseEntity = basicAuthTemplate(TEST_USER_2).postForEntity("/notices/1", htmlFormDataBuilder.build(), String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void delete_by_owner() {
        htmlFormDataBuilder = HtmlFormDataBuilder.urlEncodedForm().delete();
        ResponseEntity<String> responseEntity = basicAuthTemplate(TEST_USER_1).postForEntity("/notices/1", htmlFormDataBuilder.build(), String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        softly.assertThat(noticeRepository.findById(1L).isPresent()).isFalse();
    }
}
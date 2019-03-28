package com.assignment.rsupport.noticejava.web;

import com.assignment.rsupport.noticejava.domain.user.UserRepository;
import com.assignment.rsupport.support.test.AcceptanceTest;
import com.assignment.rsupport.support.test.HtmlFormDataBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserAcceptanceTest extends AcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(UserAcceptanceTest.class);

    private HtmlFormDataBuilder htmlFormDataBuilder;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createForm() {
        ResponseEntity<String> responseEntity = template().getForEntity("/users/form", String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        logger.debug("body : {}", responseEntity.getBody());
    }

    @Test
    public void create() {
        htmlFormDataBuilder = HtmlFormDataBuilder.urlEncodedForm()
                .addParameter("userName", "sehun")
                .addParameter("userId", "sechune")
                .addParameter("password", "1234");

        ResponseEntity<String> responseEntity = template().postForEntity("/users", htmlFormDataBuilder.build(), String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        softly.assertThat(userRepository.findByUserId("sechune").isPresent()).isTrue();
        softly.assertThat(responseEntity.getHeaders().getLocation().getPath()).startsWith("/login");
    }
}
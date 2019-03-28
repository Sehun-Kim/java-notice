package com.assignment.rsupport.noticejava.web;

import com.assignment.rsupport.support.test.AcceptanceTest;
import com.assignment.rsupport.support.test.HtmlFormDataBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LoginAcceptanceTest extends AcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginAcceptanceTest.class);

    private HtmlFormDataBuilder htmlFormDataBuilder;

    @Test
    public void form() {
        ResponseEntity<String> responseEntity = template().getForEntity("/login", String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(responseEntity.getBody().contains("Login")).isTrue();
        logger.debug("body : {}", responseEntity.getBody());
    }

    @Test
    public void login_fail() {
        htmlFormDataBuilder = HtmlFormDataBuilder.urlEncodedForm()
                .addParameter("userId", "alba")
                .addParameter("password", "1234");

        ResponseEntity<String> responseEntity = template().postForEntity("/login", htmlFormDataBuilder.build(), String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        softly.assertThat(responseEntity.getHeaders().getLocation().getPath().startsWith("/login")).isTrue();
    }

    @Test
    public void login_success() {
        htmlFormDataBuilder = HtmlFormDataBuilder.urlEncodedForm()
                .addParameter("userId", "tester")
                .addParameter("password", "1234");

        ResponseEntity<String> responseEntity = template().postForEntity("/login", htmlFormDataBuilder.build(), String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        softly.assertThat(responseEntity.getHeaders().getLocation().getPath().startsWith("/notices")).isTrue();
    }
}
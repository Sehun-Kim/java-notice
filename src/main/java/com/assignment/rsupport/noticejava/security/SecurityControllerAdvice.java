package com.assignment.rsupport.noticejava.security;

import com.assignment.rsupport.noticejava.exception.UnAuthenticationException;
import com.assignment.rsupport.noticejava.exception.UnAuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice(annotations = Controller.class)
public class SecurityControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(SecurityControllerAdvice.class);

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void emptyResultData() {
        logger.debug("EntityNotFoundException is happened!");
    }

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public String unAuthentication() {
        logger.debug("UnAuthenticationException is happened!");
        return "user/login";
    }

    @ExceptionHandler(UnAuthorizationException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public void unAuthorization() {
        logger.debug("UnAuthorizationException is happened!");
    }
}

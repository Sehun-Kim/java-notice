package com.assignment.rsupport.noticejava.security;

import com.assignment.rsupport.noticejava.exception.UnAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(annotations = Controller.class)
public class SecurityControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(SecurityControllerAdvice.class);

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public String unAuthentication(Model model) {
        logger.debug("UnAuthenticationException is happened!");
//        model.addAttribute("errorMessage", "UnAuthentication");
        return "/login";
    }
}

package com.assignment.rsupport.noticejava.security;

import com.assignment.rsupport.noticejava.exception.UnAuthenticationException;
import com.assignment.rsupport.noticejava.exception.UnAuthorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice(annotations = Controller.class)
public class SecurityControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(SecurityControllerAdvice.class);

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.FOUND)
    public String emptyResultData() {
        logger.debug("EntityNotFoundException is happened!");
        return "redirect:/notices";
    }

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.FOUND)
    public String unAuthentication(Model model) {
        logger.debug("UnAuthenticationException is happened!");
//        model.addAttribute("errorMessage", "UnAuthentication");
        return "redirect:/login";
    }

    @ExceptionHandler(UnAuthorization.class)
    @ResponseStatus(value = HttpStatus.FOUND)
    public String unAuthorization(Model model) {
        logger.debug("UnAuthorizationException is happened!");
//        model.addAttribute("errorMessage", "UnAuthentication");
        return "redirect:/notices";
    }
}

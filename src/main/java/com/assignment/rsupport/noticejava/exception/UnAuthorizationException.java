package com.assignment.rsupport.noticejava.exception;

public class UnAuthorizationException extends RuntimeException {
    public UnAuthorizationException() {
    }

    public UnAuthorizationException(String message) {
        super(message);
    }
}

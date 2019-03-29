package com.assignment.rsupport.noticejava.exception;

public class UnAuthorization extends RuntimeException {
    public UnAuthorization() {
    }

    public UnAuthorization(String message) {
        super(message);
    }
}

package com.nhnacademy.store99.bookstore.common.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super("BadRequestException: " + message);
    }
}

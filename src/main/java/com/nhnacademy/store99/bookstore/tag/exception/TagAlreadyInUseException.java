package com.nhnacademy.store99.bookstore.tag.exception;

import org.springframework.http.HttpStatus;


public class TagAlreadyInUseException extends RuntimeException{
    public TagAlreadyInUseException(HttpStatus status, String message) {
        super(message);
    }
}

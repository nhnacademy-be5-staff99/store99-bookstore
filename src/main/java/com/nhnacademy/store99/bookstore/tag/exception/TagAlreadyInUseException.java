package com.nhnacademy.store99.bookstore.tag.exception;

import org.springframework.http.HttpStatus;

/**
 * 태그 REST 컨트롤러
 *
 * @Author rosin23
 */

public class TagAlreadyInUseException extends RuntimeException{
    public TagAlreadyInUseException(HttpStatus status, String message) {
        super(message);
    }
}

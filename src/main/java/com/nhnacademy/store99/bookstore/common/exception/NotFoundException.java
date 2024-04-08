package com.nhnacademy.store99.bookstore.common.exception;

/**
 * NotFoundException
 * <p>404 NOT_FOUND Exception
 *
 * @author seunggyu-kim
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}

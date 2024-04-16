package com.nhnacademy.store99.bookstore.common.exception;

public class MissingUserIdHeaderException extends RuntimeException {
    public MissingUserIdHeaderException() {
        super("X-USER-ID 헤더가 없습니다.");
    }
}

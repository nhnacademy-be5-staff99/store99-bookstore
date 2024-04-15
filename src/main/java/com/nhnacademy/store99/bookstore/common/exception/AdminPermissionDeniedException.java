package com.nhnacademy.store99.bookstore.common.exception;

/**
 * 관리자 권한이 없을 때 발생하는 에러
 *
 * @author seunggyu-kim
 */
public class AdminPermissionDeniedException extends RuntimeException {
    public AdminPermissionDeniedException() {
        super("관리자 권한이 없습니다.");
    }
}

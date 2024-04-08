package com.nhnacademy.store99.bookstore.auth.service;

/**
 * 관리자 권한 체크 Service Interface
 *
 * @author seunggyu-kim
 */
public interface AdminCheckService {
    Boolean isAdmin(Long userId);
}

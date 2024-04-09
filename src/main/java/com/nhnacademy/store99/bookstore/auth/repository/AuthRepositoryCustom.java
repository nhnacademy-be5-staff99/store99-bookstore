package com.nhnacademy.store99.bookstore.auth.repository;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * Custom Repository for Auth
 *
 * @author seunggyu-kim
 */
@NoRepositoryBean
public interface AuthRepositoryCustom {
    String getAuth(Long userId);
}

package com.nhnacademy.store99.bookstore.auth.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AuthRepositoryCustom {
    String getAuth(Long userId);
}

package com.nhnacademy.store99.bookstore.auth.repository;

import com.nhnacademy.store99.bookstore.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
}

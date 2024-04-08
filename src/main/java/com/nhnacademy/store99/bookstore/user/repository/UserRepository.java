package com.nhnacademy.store99.bookstore.user.repository;

import com.nhnacademy.store99.bookstore.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 * <p>JPA Repository for User Entity
 *
 * @author seunggyu-kim
 */
public interface UserRepository extends JpaRepository<User, Long> {

}

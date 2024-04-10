package com.nhnacademy.store99.bookstore.user.repository;

import com.nhnacademy.store99.bookstore.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepository extends JpaRepository<User, Long> {

}

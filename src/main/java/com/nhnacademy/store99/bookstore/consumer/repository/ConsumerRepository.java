package com.nhnacademy.store99.bookstore.consumer.repository;

import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    boolean existsByConsumerEmail(String email);
}

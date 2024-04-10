package com.nhnacademy.store99.bookstore.consumer.repository;

import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    boolean existsByConsumerPassword(String password);
}

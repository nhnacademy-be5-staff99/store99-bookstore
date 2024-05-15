package com.nhnacademy.store99.bookstore.order.repository;

import com.nhnacademy.store99.bookstore.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author seunggyu-kim
 */
public interface OrderRepository extends JpaRepository<Order, String>, OrderRepositoryCustom {
}
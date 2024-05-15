package com.nhnacademy.store99.bookstore.payment.repository;

import com.nhnacademy.store99.bookstore.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}

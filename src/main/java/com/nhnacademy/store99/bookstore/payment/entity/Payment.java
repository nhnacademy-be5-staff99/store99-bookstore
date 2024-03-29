package com.nhnacademy.store99.bookstore.payment.entity;

import com.nhnacademy.store99.bookstore.order.entity.Order;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "payments", schema = "staff99_bookstore")
public class Payment {
    @Id
    @Column(name = "payment_key", nullable = false, length = 200)
    private String paymentKey;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
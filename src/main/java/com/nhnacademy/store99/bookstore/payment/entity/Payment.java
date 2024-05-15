package com.nhnacademy.store99.bookstore.payment.entity;

import com.nhnacademy.store99.bookstore.order.entity.Order;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Payment Entity
 *
 * @author seunggyu-kim
 */
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

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Size(max = 20)
    @NotNull
    @Column(name = "payment_method", nullable = false, length = 20)
    private String paymentMethod;

    @Lob
    @Column(name = "payment_response")
    private String paymentResponse;

    @Column(name = "payment_cost")
    private Integer paymentCost;
}
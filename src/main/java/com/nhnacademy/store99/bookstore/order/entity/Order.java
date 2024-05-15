package com.nhnacademy.store99.bookstore.order.entity;

import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import com.nhnacademy.store99.bookstore.order.enums.OrderState;
import com.nhnacademy.store99.bookstore.order.enums.OrderType;
import com.nhnacademy.store99.bookstore.payment.entity.Payment;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Order Entity
 *
 * @author seunggyu-kim
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "orders", schema = "staff99_bookstore")
public class Order {
    @Id
    @Column(name = "order_id", nullable = false, length = 36)
    private String orderId;     // UUID

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false, length = 10)
    private OrderType orderType;

    @Builder.Default
    @Column(name = "order_at", nullable = false)
    private LocalDateTime orderAt = LocalDateTime.now();

    @Column(name = "order_transit_at", nullable = false)
    private LocalDate orderTransitAt;

    @Column(name = "order_receiver_name", nullable = false, length = 50)
    private String orderReceiverName;

    @Column(name = "order_receiver_number", nullable = false, length = 11)
    private String orderReceiverNumber;

    @Column(name = "order_address", nullable = false)
    private String orderAddress;

    @Column(name = "order_address_detail", nullable = false)
    private String orderAddressDetail;

    @Column(name = "order_address_code", nullable = false)
    private Integer orderAddressCode;

    @Column(name = "order_delivery_cost", nullable = false)
    private Integer orderDeliveryCost = 5000;

    @Column(name = "order_total_cost", nullable = false)
    private Integer orderTotalCost;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "order_state", nullable = false, length = 20)
    private OrderState orderState;

    @Column(name = "order_release_at")
    private LocalDateTime orderReleaseAt;

    @Column(name = "coupon_id")
    private Long couponId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consumer_id", nullable = false)
    private Consumer consumer;

    @OneToOne(mappedBy = "order")
    private Payment payment;
}
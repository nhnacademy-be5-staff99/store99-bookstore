package com.nhnacademy.store99.bookstore.order.dto;

import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import com.nhnacademy.store99.bookstore.order.entity.Order;
import com.nhnacademy.store99.bookstore.order.enums.OrderState;
import com.nhnacademy.store99.bookstore.order.enums.OrderType;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author seunggyu-kim
 */
@Getter
public class OrderDto {
    private String orderId;     // UUID

    private OrderType orderType;

    private LocalDate orderTransitAt;

    private String orderReceiverName;

    private String orderReceiverNumber;

    private String orderAddress;

    private String orderAddressDetail;

    private Integer orderAddressCode;

    private Integer orderDeliveryCost;

    private Integer orderTotalCost;

    public Order toEntity(@NonNull final Consumer consumer) {
        return Order.builder()
                .orderId(orderId)
                .orderType(orderType)
                .orderTransitAt(orderTransitAt)
                .orderReceiverName(orderReceiverName)
                .orderReceiverNumber(orderReceiverNumber)
                .orderAddress(orderAddress)
                .orderAddressDetail(orderAddressDetail)
                .orderAddressCode(orderAddressCode)
                .orderDeliveryCost(orderDeliveryCost)
                .orderTotalCost(orderTotalCost)
                .orderState(OrderState.PAYMENT_PENDING)
                .consumer(consumer)
                .build();
    }
}

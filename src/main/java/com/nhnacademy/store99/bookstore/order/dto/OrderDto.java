package com.nhnacademy.store99.bookstore.order.dto;

import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import com.nhnacademy.store99.bookstore.order.entity.Order;
import com.nhnacademy.store99.bookstore.order.enums.OrderState;
import com.nhnacademy.store99.bookstore.order.enums.OrderType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author seunggyu-kim
 */
@Getter
public class OrderDto {
    @Size(max = 36)
    @NotNull
    private String orderId;     // UUID

    @Size(max = 255)
    @NotNull
    private String orderName;

    @NotNull
    private OrderType orderType;

    @NotNull
    private LocalDate orderTransitAt;

    @Size(max = 50)
    @NotNull
    private String orderReceiverName;

    @Size(max = 11)
    @NotNull
    private String orderReceiverNumber;

    @Size(max = 255)
    @NotNull
    private String orderAddress;

    @Size(max = 255)
    @NotNull
    private String orderAddressDetail;

    @NotNull
    private Integer orderAddressCode;

    @NotNull
    private Integer orderDeliveryCost;

    @NotNull
    private Integer orderTotalCost;

    private Integer orderUsePoint;

    @NotNull
    private Integer orderFinalCost;

    private Long couponId;

    private Integer couponDiscount;

    public Order toEntity(@NonNull final Consumer consumer) {
        return Order.builder()
                .orderId(orderId)
                .orderName(orderName)
                .orderType(orderType)
                .orderAt(LocalDateTime.now())
                .orderTransitAt(orderTransitAt)
                .orderReceiverName(orderReceiverName)
                .orderReceiverNumber(orderReceiverNumber)
                .orderAddress(orderAddress)
                .orderAddressDetail(orderAddressDetail)
                .orderAddressCode(orderAddressCode)
                .orderDeliveryCost(orderDeliveryCost)
                .orderTotalCost(orderTotalCost)
                .orderFinalCost(orderFinalCost)
                .orderState(OrderState.PAYMENT_PENDING)
                .consumer(consumer)
                .build();
    }
}

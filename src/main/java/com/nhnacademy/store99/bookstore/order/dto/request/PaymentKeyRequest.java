package com.nhnacademy.store99.bookstore.order.dto.request;

import lombok.Getter;

@Getter
public class PaymentKeyRequest {
    private String paymentKey;

    private String response;

    private String method;

    private Integer paymentCost;
}

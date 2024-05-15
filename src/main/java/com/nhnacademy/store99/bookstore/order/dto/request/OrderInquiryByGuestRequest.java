package com.nhnacademy.store99.bookstore.order.dto.request;

import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
public class OrderInquiryByGuestRequest {
    private String orderId;

    private String orderPassword;
}

package com.nhnacademy.store99.bookstore.order.service;

import com.nhnacademy.store99.bookstore.order.dto.request.OrderInquiryByGuestRequest;
import com.nhnacademy.store99.bookstore.order.dto.response.OrderInquiryResponse;

/**
 * @author seunggyu-kim
 */
public interface OrderQueryService {
    OrderInquiryResponse getOrderByGuest(OrderInquiryByGuestRequest orderInquiryRequest);
}

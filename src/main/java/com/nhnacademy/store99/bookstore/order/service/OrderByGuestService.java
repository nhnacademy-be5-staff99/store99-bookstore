package com.nhnacademy.store99.bookstore.order.service;

import com.nhnacademy.store99.bookstore.order.dto.request.ConfirmPaymentRequest;

/**
 * @author seunggyu-kim
 */
public interface OrderByGuestService {
    void confirmPayment(final ConfirmPaymentRequest confirmPaymentRequest);

    void undoPendingPayment(String orderId);

    void successPendingPayment(String orderId, String paymentKey);

    void failPendingPayment(String orderId, String paymentKey);
}
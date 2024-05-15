package com.nhnacademy.store99.bookstore.order.service;

import com.nhnacademy.store99.bookstore.order.dto.request.ConfirmPaymentRequest;
import com.nhnacademy.store99.bookstore.order.dto.request.PaymentKeyRequest;

/**
 * @author seunggyu-kim
 */
public interface OrderByGuestService {
    void confirmPayment(final ConfirmPaymentRequest confirmPaymentRequest);

    void undoPendingPayment(String orderId);

    void successPendingPayment(String orderId, PaymentKeyRequest paymentKeyRequest);

    void failPendingPayment(String orderId, String paymentKey);
}
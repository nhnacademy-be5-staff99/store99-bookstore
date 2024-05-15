package com.nhnacademy.store99.bookstore.order.controller;

import com.nhnacademy.store99.bookstore.order.dto.request.ConfirmPaymentRequest;
import com.nhnacademy.store99.bookstore.order.dto.request.PaymentKeyRequest;
import com.nhnacademy.store99.bookstore.order.service.OrderByGuestService;
import com.nhnacademy.store99.bookstore.order.service.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author seunggyu-kim
 */
@RestController
@RequestMapping("/open/v1/orders")
@RequiredArgsConstructor
public class OrderByGuestController {
    private final OrderQueryService orderQueryService;
    private final OrderByGuestService orderByGuestService;

    /**
     * 결제 버튼을 눌렀을 때, 구매자, 주문, 주문-도서 테이블 생성
     * 주문 상태는 PAYMENT_PENDING(결제 대기)로 생성
     *
     * @param confirmPaymentRequest 결제 요청 정보
     */
    @PostMapping
    public void confirmPayment(@RequestBody ConfirmPaymentRequest confirmPaymentRequest) {
        orderByGuestService.confirmPayment(confirmPaymentRequest);
    }

    /**
     * 결제 취소 시, DB에서 주문 데이터 삭제
     */
    @DeleteMapping("/{orderId}")
    public void undoPendingPayment(@PathVariable String orderId) {
        orderByGuestService.undoPendingPayment(orderId);
    }

    /**
     * 결제 성공 시, 주문 상태를 PAYMENT_COMPLETED(결제 완료)로 변경
     */
    @PostMapping("/{orderId}/success")
    public void successPendingPayment(@PathVariable String orderId,
                                      @RequestBody PaymentKeyRequest paymentKeyRequest) {
        orderByGuestService.successPendingPayment(orderId, paymentKeyRequest);
    }

    /**
     * 결제 실패 시, 주문 상태를 PAYMENT_FAILED(결제 실패)로 변경
     */
    @PostMapping("/{orderId}/fail")
    public void failPendingPayment(@PathVariable String orderId,
                                   @RequestBody PaymentKeyRequest paymentKeyRequest) {
        orderByGuestService.failPendingPayment(orderId, paymentKeyRequest.getPaymentKey());
    }
}

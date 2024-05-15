package com.nhnacademy.store99.bookstore.order.enums;

/**
 * OrderState enum
 *
 * @author seunggyu-kim
 */
public enum OrderState {
    PAYMENT_PENDING("결제 대기"),
    PAYMENT_COMPLETED("결제 완료"),
    TRANSIT("배송 중"),
    COMPLETED("취소"),
    RETURNED("반품"),
    CANCELLED("취소"),
    PAYMENT_FAILED("결제 실패");

    private final String value;

    OrderState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

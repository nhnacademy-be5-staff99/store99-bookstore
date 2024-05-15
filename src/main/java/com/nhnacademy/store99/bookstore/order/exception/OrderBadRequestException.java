package com.nhnacademy.store99.bookstore.order.exception;

import com.nhnacademy.store99.bookstore.common.exception.BadRequestException;

public class OrderBadRequestException extends BadRequestException {
    public OrderBadRequestException(String message) {
        super(String.format("Bad request in Order: %s", message));
    }
}

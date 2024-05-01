package com.nhnacademy.store99.bookstore.address.exception;

import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;

public class AddressNotFoundException extends NotFoundException {
    public AddressNotFoundException(Long userId) {
        super(String.format("User not found (userId: %d)", userId));
    }
}

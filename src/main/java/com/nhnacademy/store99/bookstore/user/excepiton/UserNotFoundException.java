package com.nhnacademy.store99.bookstore.user.excepiton;

import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Long userId) {
        super(String.format("User not found (userId: %d)", userId));
    }
}

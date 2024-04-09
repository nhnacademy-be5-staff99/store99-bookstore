package com.nhnacademy.store99.bookstore.user.exception;

import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;

public class UserNotFoundByEmailException extends NotFoundException {
    public UserNotFoundByEmailException(String email) {
        super(String.format("User not found (UserEmail: %s)", email));
    }
}

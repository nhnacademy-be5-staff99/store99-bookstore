package com.nhnacademy.store99.bookstore.address.exception;

import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;

/**
 * @author Ahyeon Song
 */
public class AddressNotFoundByUserIdException extends NotFoundException {
    public AddressNotFoundByUserIdException(Long userId) {
        super(String.format("User not found (userId: %d)", userId));
    }
}

package com.nhnacademy.store99.bookstore.address.exception;

import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;

/**
 * @author Ahyeon Song
 */
public class AddressNotFoundByAddressIdException extends NotFoundException {
    public AddressNotFoundByAddressIdException(Long addressId) {
        super(String.format("Address not found (AddressId: %d)", addressId));
    }
}

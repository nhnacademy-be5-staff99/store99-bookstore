package com.nhnacademy.store99.bookstore.address.exception;

/**
 * @author Ahyeon Song
 */
public class AddressOverTenException extends RuntimeException {
    public AddressOverTenException(Long userId) {
        super(String.format("Address Count Over Then 10 - userId : %d", userId));
    }
}

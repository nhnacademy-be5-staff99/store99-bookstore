package com.nhnacademy.store99.bookstore.cart.exception;


import com.nhnacademy.store99.bookstore.common.exception.BadRequestException;

/**
 * @author seunggyu-kimpost
 */
public class CartBadRequestException extends BadRequestException {
    public CartBadRequestException(String message) {
        super(String.format("Bad request in Cart: %s", message));
    }

    public static CartBadRequestException BookNotFound(Long bookId) {
        return new CartBadRequestException(String.format("Book not found (book id: %d)", bookId));
    }

    public static CartBadRequestException UserNotFound(Long userId) {
        return new CartBadRequestException(String.format("User not found (user id: %d)", userId));
    }
}

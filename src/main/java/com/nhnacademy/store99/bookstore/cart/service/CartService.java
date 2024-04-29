package com.nhnacademy.store99.bookstore.cart.service;

import com.nhnacademy.store99.bookstore.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.bookstore.cart.exception.CartBadRequestException;

/**
 * @author seunggyu-kim
 */
public interface CartService {
    void addBookToCart(final CartItemRequest request) throws CartBadRequestException;

    void modifyBookQuantityInCart(final CartItemRequest request) throws CartBadRequestException;
}

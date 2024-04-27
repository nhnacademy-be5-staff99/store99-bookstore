package com.nhnacademy.store99.bookstore.cart.service;

import com.nhnacademy.store99.bookstore.cart.dto.request.CartItemRequest;

/**
 * @author seunggyu-kim
 */
public interface CartService {
    void addBookToCart(final CartItemRequest request);
}

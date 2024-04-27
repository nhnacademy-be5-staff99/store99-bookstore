package com.nhnacademy.store99.bookstore.cart.service;

import com.nhnacademy.store99.bookstore.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.bookstore.cart.dto.response.CartItemResponse;
import java.util.List;

/**
 * @author seunggyu-kim
 */
public interface CartService {
    void addBookToCart(final CartItemRequest request);

    List<CartItemResponse> getCartItemsByUser();
}

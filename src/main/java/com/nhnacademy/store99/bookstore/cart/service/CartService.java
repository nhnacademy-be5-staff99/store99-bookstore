package com.nhnacademy.store99.bookstore.cart.service;

import com.nhnacademy.store99.bookstore.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.bookstore.cart.exception.CartBadRequestException;
import java.util.Map;

/**
 * @author seunggyu-kim
 */
public interface CartService {
    void addBookToCart(final CartItemRequest request) throws CartBadRequestException;

    void modifyBookQuantityInCart(final CartItemRequest request) throws CartBadRequestException;

    void removeBookInCart(final Long bookId) throws CartBadRequestException;

    void mergeCart(Map<Long, Integer> bookIdAndQuantity);
}

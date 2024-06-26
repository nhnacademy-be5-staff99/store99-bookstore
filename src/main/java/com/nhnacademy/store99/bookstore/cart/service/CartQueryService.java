package com.nhnacademy.store99.bookstore.cart.service;

import com.nhnacademy.store99.bookstore.cart.dto.response.CartItemResponse;
import java.util.List;

/**
 * @author seunggyu-kimpost
 */
public interface CartQueryService {
    List<CartItemResponse> getCartItemsByUser();
}

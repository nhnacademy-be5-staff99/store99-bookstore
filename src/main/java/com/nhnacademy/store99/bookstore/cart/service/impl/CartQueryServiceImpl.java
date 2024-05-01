package com.nhnacademy.store99.bookstore.cart.service.impl;

import com.nhnacademy.store99.bookstore.cart.dto.response.CartItemResponse;
import com.nhnacademy.store99.bookstore.cart.repository.CartRepository;
import com.nhnacademy.store99.bookstore.cart.service.CartQueryService;
import com.nhnacademy.store99.bookstore.common.thread_local.XUserIdThreadLocal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author seunggyu-kim
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartQueryServiceImpl implements CartQueryService {
    private final CartRepository cartRepository;

    @Override
    public List<CartItemResponse> getCartItemsByUser() {
        Long userId = XUserIdThreadLocal.getXUserId();
        return cartRepository.getCartItemsByUser(userId);
    }
}

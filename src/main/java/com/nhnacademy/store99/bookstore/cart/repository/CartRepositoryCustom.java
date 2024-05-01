package com.nhnacademy.store99.bookstore.cart.repository;

import com.nhnacademy.store99.bookstore.cart.dto.response.CartItemResponse;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author seunggyu-kim
 */
@NoRepositoryBean
public interface CartRepositoryCustom {
    List<CartItemResponse> getCartItemsByUser(Long userId);
}

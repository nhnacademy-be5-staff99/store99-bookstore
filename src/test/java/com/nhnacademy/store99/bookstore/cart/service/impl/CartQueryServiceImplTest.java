package com.nhnacademy.store99.bookstore.cart.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;

import com.nhnacademy.store99.bookstore.cart.dto.response.CartItemResponse;
import com.nhnacademy.store99.bookstore.cart.repository.CartRepository;
import com.nhnacademy.store99.bookstore.common.thread_local.XUserIdThreadLocal;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author seunggyu-kim
 */
@ExtendWith(MockitoExtension.class)
class CartQueryServiceImplTest {
    @InjectMocks
    private CartQueryServiceImpl cartQueryService;

    @Mock
    private CartRepository cartRepository;

    @Test
    @DisplayName("사용자의 장바구니 아이템 조회 성공")
    void getCartItemsByUserSuccessfully() {
        try (MockedStatic<XUserIdThreadLocal> utilities = mockStatic(XUserIdThreadLocal.class)) {
            Long userId = 1L;
            List<CartItemResponse> expectedResponse = Collections.emptyList();
            given(XUserIdThreadLocal.getXUserId()).willReturn(userId);
            given(cartRepository.getCartItemsByUser(userId)).willReturn(expectedResponse);

            List<CartItemResponse> actualResponse = cartQueryService.getCartItemsByUser();

            assertThat(actualResponse).isEqualTo(expectedResponse);
        }
    }
}
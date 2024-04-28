package com.nhnacademy.store99.bookstore.cart.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.repository.BookRepository;
import com.nhnacademy.store99.bookstore.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.bookstore.cart.entity.Cart;
import com.nhnacademy.store99.bookstore.cart.exception.CartBadRequestException;
import com.nhnacademy.store99.bookstore.cart.repository.CartRepository;
import com.nhnacademy.store99.bookstore.common.thread_local.XUserIdThreadLocal;
import com.nhnacademy.store99.bookstore.user.entity.User;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author seunggyu-kim
 */
@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {
    @InjectMocks
    private CartServiceImpl cartService;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("장바구니에 책 추가 - 책이 없을 경우 책 새로 추가")
    void addBookToCartWhenBookNotInCart() {
        try (MockedStatic<XUserIdThreadLocal> utilities = mockStatic(XUserIdThreadLocal.class)) {
            // given
            CartItemRequest request = new CartItemRequest(1L, 1);
            given(XUserIdThreadLocal.getXUserId()).willReturn(1L);
            given(cartRepository.findByUser_IdAndBook_Id(1L, 1L)).willReturn(Optional.empty());
            given(userRepository.findById(1L)).willReturn(Optional.of(User.builder().id(1L).build()));
            given(bookRepository.findById(1L)).willReturn(Optional.of(Book.builder().id(1L).build()));

            // when
            cartService.addBookToCart(request);

            // then
            verify(cartRepository).save(Mockito.any(Cart.class));
        }
    }

    @Test
    @DisplayName("장바구니에 책 추가 - 책이 이미 있을 경우 수량증가")
    void addBookToCartWhenBookAlreadyInCart() {
        try (MockedStatic<XUserIdThreadLocal> utilities = mockStatic(XUserIdThreadLocal.class)) {
            // given
            CartItemRequest request = new CartItemRequest(1L, 1);
            given(XUserIdThreadLocal.getXUserId()).willReturn(1L);
            Cart cart = Cart.builder().id(1L).cartAmount(1).build();
            given(cartRepository.findByUser_IdAndBook_Id(1L, 1L)).willReturn(Optional.of(cart));

            // when
            cartService.addBookToCart(request);

            // then
            verify(cartRepository, never()).save(Mockito.any(Cart.class));
            assertThat(cart.getCartAmount()).isEqualTo(2);
        }
    }

    @Test
    @DisplayName("장바구니에 책 추가 - 사용자가 없을 경우")
    void addBookToCartWhenUserNotFound() {
        try (MockedStatic<XUserIdThreadLocal> utilities = mockStatic(XUserIdThreadLocal.class)) {
            // given
            CartItemRequest request = new CartItemRequest(1L, 1);
            given(XUserIdThreadLocal.getXUserId()).willReturn(1L);
            given(cartRepository.findByUser_IdAndBook_Id(1L, 1L)).willReturn(Optional.empty());
            given(userRepository.findById(1L)).willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> cartService.addBookToCart(request))
                    .isInstanceOf(CartBadRequestException.class)
                    .hasMessageContaining("User not found (user id: 1)");
            verify(cartRepository, never()).save(Mockito.any(Cart.class));
        }
    }

    @Test
    @DisplayName("장바구니에 책 추가 - 책이 없을 경우")
    void addBookToCartWhenBookNotFound() {
        try (MockedStatic<XUserIdThreadLocal> utilities = mockStatic(XUserIdThreadLocal.class)) {
            // given
            CartItemRequest request = new CartItemRequest(1L, 1);
            given(XUserIdThreadLocal.getXUserId()).willReturn(1L);
            given(cartRepository.findByUser_IdAndBook_Id(1L, 1L)).willReturn(Optional.empty());
            given(userRepository.findById(1L)).willReturn(Optional.of(User.builder().id(1L).build()));
            given(bookRepository.findById(1L)).willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> cartService.addBookToCart(request))
                    .isInstanceOf(CartBadRequestException.class)
                    .hasMessageContaining("Book not found (book id: 1)");
            verify(cartRepository, never()).save(Mockito.any(Cart.class));
        }
    }
}
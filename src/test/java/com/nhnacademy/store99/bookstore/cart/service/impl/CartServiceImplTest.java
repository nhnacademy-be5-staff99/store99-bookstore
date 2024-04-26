package com.nhnacademy.store99.bookstore.cart.service.impl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.repository.BookRepository;
import com.nhnacademy.store99.bookstore.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.bookstore.cart.entity.Cart;
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
    @DisplayName("장바구니에 책 추가 - 책이 없을 경우")
    void addBookToCart_BookNotInCart() {
        try (MockedStatic<XUserIdThreadLocal> utilities = Mockito.mockStatic(XUserIdThreadLocal.class)) {
            // given
            CartItemRequest request = new CartItemRequest(1L, 1);
            utilities.when(XUserIdThreadLocal::getXUserId).thenReturn(1L);
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
    @DisplayName("장바구니에 책 추가 - 책이 이미 있을 경우")
    void addBookToCart_BookAlreadyInCart() {

    }

    @Test
    @DisplayName("장바구니에 책 추가 - 사용자가 없을 경우")
    void addBookToCart_UserNotFound() {

    }

    @Test
    @DisplayName("장바구니에 책 추가 - 책이 없을 경우")
    void addBookToCart_BookNotFound() {

    }
}
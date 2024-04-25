package com.nhnacademy.store99.bookstore.cart.service.impl;

import com.nhnacademy.store99.bookstore.book.repository.BookRepository;
import com.nhnacademy.store99.bookstore.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.bookstore.cart.entity.Cart;
import com.nhnacademy.store99.bookstore.cart.exception.CartBadRequestException;
import com.nhnacademy.store99.bookstore.cart.repository.CartRepository;
import com.nhnacademy.store99.bookstore.cart.service.CartService;
import com.nhnacademy.store99.bookstore.common.thread_local.XUserIdThreadLocal;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author seunggyu-kim
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void addBookToCart(final CartItemRequest request) {
        Long xUserId = XUserIdThreadLocal.getXUserId();
        Long bookId = request.getBookId();
        Optional<Cart> cartOptional = cartRepository.findByUser_IdAndBook_Id(xUserId, bookId);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.addCartAmount(request.getQuantity());
            return;
        }

        Cart cart = Cart.builder()
                .cartAmount(request.getQuantity())
                .user(userRepository.findById(xUserId).orElseThrow(() -> CartBadRequestException.UserNotFound(xUserId)))
                .book(bookRepository.findById(bookId).orElseThrow(() -> CartBadRequestException.BookNotFound(bookId)))
                .build();

        cartRepository.save(cart);
    }
}

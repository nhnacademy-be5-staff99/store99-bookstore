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
@Transactional
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public void addBookToCart(final CartItemRequest request) throws CartBadRequestException {
        Long xUserId = XUserIdThreadLocal.getXUserId();
        Long bookId = request.getBookId();
        Optional<Cart> cartOptional = cartRepository.findByUser_IdAndBook_Id(xUserId, bookId);

        // 이미 장바구니에 있는 경우
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.addCartAmount(request.getQuantity());
            return;
        }

        // 장바구니에 없는 경우
        Cart cart = Cart.builder()
                .cartAmount(request.getQuantity())
                .user(userRepository.findById(xUserId).orElseThrow(() -> CartBadRequestException.UserNotFound(xUserId)))
                .book(bookRepository.findById(bookId).orElseThrow(() -> CartBadRequestException.BookNotFound(bookId)))
                .build();

        cartRepository.save(cart);
    }

    @Override
    public void modifyBookQuantityInCart(final CartItemRequest request) throws CartBadRequestException {
        Long xUserId = XUserIdThreadLocal.getXUserId();
        Long bookId = request.getBookId();
        Cart cart = cartRepository.findByUser_IdAndBook_Id(xUserId, bookId)
                .orElseThrow(() -> new CartBadRequestException("Modify failed. Cart not found."));

        cart.modifyCartAmount(request.getQuantity());
    }

    @Override
    public void removeBookInCart(final Long bookId) throws CartBadRequestException {
        Long xUserId = XUserIdThreadLocal.getXUserId();
        Cart cart = cartRepository.findByUser_IdAndBook_Id(xUserId, bookId)
                .orElseThrow(() -> new CartBadRequestException("Remove failed. Cart not found."));

        cartRepository.delete(cart);
    }
}

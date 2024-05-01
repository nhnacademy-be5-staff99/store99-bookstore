package com.nhnacademy.store99.bookstore.cart.repository.impl;

import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.cart.dto.response.CartItemResponse;
import com.nhnacademy.store99.bookstore.cart.dto.response.QCartItemResponse;
import com.nhnacademy.store99.bookstore.cart.entity.Cart;
import com.nhnacademy.store99.bookstore.cart.entity.QCart;
import com.nhnacademy.store99.bookstore.cart.repository.CartRepositoryCustom;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * @author seunggyu-kim
 */
public class CartRepositoryImpl extends QuerydslRepositorySupport implements CartRepositoryCustom {
    public CartRepositoryImpl() {
        super(Cart.class);
    }

    @Override
    public List<CartItemResponse> getCartItemsByUser(Long userId) {
        QCart cart = QCart.cart;
        QBook book = QBook.book;

        return from(cart)
                .innerJoin(book).on(cart.book.eq(book))
                .where(cart.user.id.eq(userId))
                .select(new QCartItemResponse(
                        book.id,
                        book.bookTitle,
                        book.bookPrice,
                        book.bookSalePrice,
                        book.bookThumbnailUrl,
                        book.bookStock,
                        cart.cartAmount
                ))
                .fetch();
    }
}

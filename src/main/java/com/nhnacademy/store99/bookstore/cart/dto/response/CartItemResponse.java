package com.nhnacademy.store99.bookstore.cart.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class CartItemResponse {
    private final Long bookId;

    private final String bookTitle;

    private final Integer bookPrice;

    private final Integer bookSalePrice;

    private final String bookThumbnailUrl;

    private final Integer bookStock;

    private final Integer quantity;

    @QueryProjection
    public CartItemResponse(final Long bookId, final String bookTitle, final Integer bookPrice,
                            final Integer bookSalePrice,
                            final String bookThumbnailUrl, final Integer bookStock, final Integer quantity) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
        this.bookSalePrice = bookSalePrice;
        this.bookThumbnailUrl = bookThumbnailUrl;
        this.bookStock = bookStock;
        this.quantity = quantity;
    }
}
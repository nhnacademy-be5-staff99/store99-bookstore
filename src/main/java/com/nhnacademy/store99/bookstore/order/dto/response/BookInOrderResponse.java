package com.nhnacademy.store99.bookstore.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
@AllArgsConstructor
public class BookInOrderResponse {
    private Long bookId;
    private String bookTitle;
    private Integer bookPrice;
    private Integer bookSalePrice;
    private String bookThumbnailUrl;
    private Integer bookStock;
    private Integer quantity;
}
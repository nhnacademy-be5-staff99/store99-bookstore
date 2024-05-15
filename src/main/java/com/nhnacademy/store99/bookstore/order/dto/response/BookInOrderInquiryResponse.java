package com.nhnacademy.store99.bookstore.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
@AllArgsConstructor
public class BookInOrderInquiryResponse {
    private Long bookId;
    private String bookTitle;
    private Integer quantity;
    private Integer bookSalePrice;
}

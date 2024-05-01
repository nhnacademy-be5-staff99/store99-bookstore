package com.nhnacademy.store99.bookstore.book.dto.response;

/**
 * @author seunggyu-kim
 */
public interface SimpleBookResponse {
    Long getId();

    String getBookTitle();

    Integer getBookPrice();

    Integer getBookSalePrice();

    String getBookThumbnailUrl();

    Integer getBookStock();
}

package com.nhnacademy.store99.bookstore.order_book.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IndexBookResponse {
    private Long BookId;
    private String BookTitle;
    private String BookDescription;
    private String BookThumbnailUrl;
}

package com.nhnacademy.store99.bookstore.like.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BookInfoForLikeResponse {

    private Long bookId;

    private String bookThumbnailUrl;

    private String bookTitle;

    private Integer bookPrice;

    private Integer bookSalePrice;


}

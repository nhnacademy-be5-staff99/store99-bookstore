package com.nhnacademy.store99.bookstore.like.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookInfoForLikeResponse {

    private Long bookId;

    private String bookThumbnailUrl;

    private String bookTitle;

    private Integer bookPrice;

    private Integer bookSalePrice;

//    private List<BookInfoForLikeResponse.AuthorDTO> authorDTOList;

    @QueryProjection
    public BookInfoForLikeResponse(Long bookId, String bookThumbnailUrl, String bookTitle, Integer bookPrice,
                                   Integer bookSalePrice) {
        this.bookId = bookId;
        this.bookThumbnailUrl = bookThumbnailUrl;
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
        this.bookSalePrice = bookSalePrice;
//        this.authorDTOList = authorDTOList;
    }

}

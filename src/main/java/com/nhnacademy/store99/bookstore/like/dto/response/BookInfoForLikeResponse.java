package com.nhnacademy.store99.bookstore.like.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Getter
    @Setter
    @Builder
    public static class AuthorDTO {
        private String authorName;
        private String authorType;

        public AuthorDTO(String authorName, String authorType) {
            this.authorName = authorName;
            this.authorType = authorType;
        }
    }
}

package com.nhnacademy.store99.bookstore.book_author.response;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TransDTO {

    private Long BookId;

    private String BookBookIsbn13;

    private String BookBookIsbn10;

    private String BookBookTitle;

    private String BookBookContents;

    private String BookBookPublisher;

    private LocalDateTime BookBookDate;

    private Integer BookBookPrice;

    private Integer BookBookSalePrice;

    private Boolean BookBookIsPacked;

    private String BookBookThumbnailUrl;

    private Integer BookBookStock;

    private Integer BookBookCntOfReview;

    private Double BookBookAvgOfRate;

    private LocalDateTime BookCreatedAt;

    private LocalDateTime BookUpdatedAt;

    //    @Builder.Default
    private List<AuthorDTO> authorsDTOList;

    @Getter
    @Setter
    @Builder
    public static class AuthorDTO {
        private String AuthorName;
        private String AuthorType;

        @QueryProjection
        public AuthorDTO(String authorName, String authorType) {
            AuthorName = authorName;
            AuthorType = authorType;
        }
    }

}

package com.nhnacademy.store99.bookstore.book.response;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookListElementDTO {

    private Long BookId;

    private String BookTitle;

    private String BookPublisher;

    private LocalDateTime BookDate;

    private Integer BookPrice;

    private Integer BookSalePrice;

    private String BookThumbnailUrl;

    private Integer BookCntOfReview;

    private Integer BookViewCount;

    private Integer BookStock;

    private Double BookAvgOfRate;

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

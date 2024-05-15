package com.nhnacademy.store99.bookstore.book.dto.response;


import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 도서 통신 데이터
 *
 * @author yrrho2
 */
@Getter
@Setter
public class BookResponse {
    private Long BookId;
    private String BookIsbn13;
    private String BookIsbn10;
    private String BookTitle;
    private String BookContents;
    private String BookDescription;
    private String BookPublisher;
    private LocalDateTime BookDate;
    private Integer BookPrice;
    private Integer BookSalePrice;
    private Integer BookStock;
    private Integer BookCntOfReview;
    private Double BookAvgOfRate;
    private String BookImageURL;
    private String BookImageName;
    private List<BookResponse.AuthorDTO> authorsDTOList;
    private List<BookResponse.TagDTO> tagDTOList;

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

    @Getter
    @Setter
    @Builder
    public static class TagDTO {
        private String TagName;

        @QueryProjection
        public TagDTO(String tagName) {
            TagName = tagName;
        }
    }
}

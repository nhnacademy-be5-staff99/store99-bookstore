package com.nhnacademy.store99.bookstore.book.response;


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
public class BookRequest {
    private Long BookId;
    private String BookIsbn13;
    private String BookIsbn10;
    private String BookTitle;
    private String BookContents;
    private String BookPublisher;
    private LocalDateTime BookDate;
    private Integer BookPrice;
    private Integer BookSalePrice;
    private Boolean BookIsPacked;
    private Integer BookStock;
    private Integer BookCntOfReview;
    private Double BookAvgOfRate;
    private Long FileId;
    private String BookImageURL;
    private String BookImageName;
    private List<BookRequest.AuthorDTO> authorsDTOList;

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

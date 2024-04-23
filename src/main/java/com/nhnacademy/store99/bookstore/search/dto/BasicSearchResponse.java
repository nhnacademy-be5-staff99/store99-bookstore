package com.nhnacademy.store99.bookstore.search.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 도서 검색 시 응답 형식
 *
 * @author Ahyeon Song
 */
@Getter
@AllArgsConstructor
public class BasicSearchResponse {

    private String bookTitle;
    private String bookThumbnailUrl;
    private List<BookAuthorInfo> bookAuthorInfos;
    private String bookPublisher;
    private LocalDateTime bookDate;
    private Integer bookPrice;
    private Integer bookSalePrice;
    private Integer bookCntOfReview;

    @Getter
    @AllArgsConstructor
    public static class BookAuthorInfo {
        private String authorName;
        private String authorType;
    }

}

package com.nhnacademy.store99.bookstore.book.response;


import java.time.LocalDateTime;
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
    private String bookIsbn13;
    private String bookIsbn10;
    private String bookTitle;
    private String bookContents;
    private String bookDescription;
    private String bookPublisher;
    private LocalDateTime bookDateTime;
    private Integer bookPrice;
    private Integer bookSalePrice;
    private Boolean bookIsPacked;
    private String bookThumbnailUrl;
    private Integer bookStock;
}

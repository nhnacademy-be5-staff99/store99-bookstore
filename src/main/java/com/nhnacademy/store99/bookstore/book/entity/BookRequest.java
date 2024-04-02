package com.nhnacademy.store99.bookstore.book.entity;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String bookIsbn13;
    private String bookIsbn11;
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

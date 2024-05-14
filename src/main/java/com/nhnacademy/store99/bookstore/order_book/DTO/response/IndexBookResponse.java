package com.nhnacademy.store99.bookstore.order_book.DTO.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndexBookResponse {
    private Long BookId;
    private String BookTitle;
    private LocalDateTime BookDate;
    private String BookDescription;
    private String BookThumbnailUrl;
    private String BookDetailURL;
}

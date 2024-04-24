package com.nhnacademy.store99.bookstore.book_author.response;

import com.nhnacademy.store99.bookstore.author.response.AuthorDTO;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookTransDTO {

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

    private String BookThumbnailUrl;

    private Integer BookStock;

    private Integer BookCntOfReview;

    private Double kBookAvgOfRate;

    private LocalDateTime CreatedAt;

    private LocalDateTime UpdatedAt;

    private List<AuthorDTO> authorDTOList;
}
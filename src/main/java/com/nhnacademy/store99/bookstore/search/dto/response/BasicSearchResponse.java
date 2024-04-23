package com.nhnacademy.store99.bookstore.search.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicSearchResponse {
    private final List<BookResponse> books;
    private final List<AuthorResponse> authors;

    public BasicSearchResponse(List<BookResponse> books, List<AuthorResponse> authors){
        this.books = books;
        this.authors = authors;
    }

    public class BookResponse {
        private String bookTitle;
        private String bookThumbnailUrl;

        private String bookPublisher;
        private LocalDateTime bookDate;
        private Integer bookPrice;
        private Integer bookSalePrice;
        private Integer bookCntOfReview = 0;


    }

    public class AuthorResponse {

    }

}

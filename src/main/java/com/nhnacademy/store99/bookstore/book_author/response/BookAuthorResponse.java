package com.nhnacademy.store99.bookstore.book_author.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthorResponse {
    private String authorName;
    private String AuthorType;
}
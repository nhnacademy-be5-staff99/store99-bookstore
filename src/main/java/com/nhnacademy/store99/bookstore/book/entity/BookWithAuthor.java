package com.nhnacademy.store99.bookstore.book.entity;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookWithAuthor {
    Book book;
    String authorName;
}

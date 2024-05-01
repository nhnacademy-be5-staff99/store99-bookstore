package com.nhnacademy.store99.bookstore.book_author.response;

import com.nhnacademy.store99.bookstore.book.entity.Book;

public interface BookAuthorAPIResponse {
    Book getBook();

    String getAuthorAuthorName();
}

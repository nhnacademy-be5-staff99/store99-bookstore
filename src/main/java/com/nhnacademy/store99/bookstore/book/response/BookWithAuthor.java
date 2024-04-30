package com.nhnacademy.store99.bookstore.book.response;


import com.nhnacademy.store99.bookstore.author.entity.Author;
import com.nhnacademy.store99.bookstore.book.entity.Book;

public interface BookWithAuthor {
    Book getBook();

    Author getAuthor();
}

package com.nhnacademy.store99.bookstore.book.entity;


import com.nhnacademy.store99.bookstore.author.entity.Author;

public interface BookWithAuthor {
    Book getBook();

    Author getAuthor();
}

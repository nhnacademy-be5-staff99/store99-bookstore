package com.nhnacademy.store99.bookstore.book_author.service;

import com.nhnacademy.store99.bookstore.author.entity.Author;
import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorDTO;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorName;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorResponse;
import java.util.List;

/**
 * 도서 작가 인터페이스
 *
 * @author yrrho2
 */
public interface BookAuthorService {

    List<BookAuthor> getAuthorByAuthorId(Long id);

    BookAuthorResponse getAuthorBook(Long id);

    List<BookAuthorDTO> getBookClassWithAuthorName(Long id);

    List<Author> test();

    List<BookAuthorName> getTuple();
}

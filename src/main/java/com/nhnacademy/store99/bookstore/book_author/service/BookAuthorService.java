package com.nhnacademy.store99.bookstore.book_author.service;

import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import java.util.List;
import java.util.Optional;

/**
 * 도서 작가 인터페이스
 *
 * @author yrrho2
 */
public interface BookAuthorService {

    List<BookAuthor> getAuthorByAuthorId(Long id);

    Optional<BookAuthor> getAuthorBook(Long id);
}

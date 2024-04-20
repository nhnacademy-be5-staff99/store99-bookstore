package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.BookRequest;
import com.nhnacademy.store99.bookstore.book.entity.BookWithAuthor;
import java.util.Optional;
import org.springframework.data.domain.Page;

/**
 * 도서 서비스 인터페이스
 *
 * @author yrrho2
 */
public interface BookServiceInterface {
    Book postBook(BookRequest bookDTO);

    Page<BookWithAuthor> getBooksAuthorName();

    Optional<Book> getBook(Long id);

    Page<Book> getBooks(int page);
}

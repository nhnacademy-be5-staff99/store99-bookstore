package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.BookRequest;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * 도서 서비스 인터페이스
 *
 * @author yrrho2
 */
@Service
public interface BookServiceInterface {
    public BookRequest postBook(BookRequest bookDTO);

    public Optional<Book> getBook(Long id);

    public Page<Book> getBooks(int page);
}

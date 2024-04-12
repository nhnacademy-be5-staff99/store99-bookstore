package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.BookRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface BookServiceInterface {
    public BookRequest postBook(BookRequest bookDTO);

    public Optional<Book> getBook(Long id);

    public List<Book> getBooks();

    public Page<Book> getBooks(int page);
}

package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.BookRequest;
import com.nhnacademy.store99.bookstore.book.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImp implements BookServiceInterface {
    final private BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookRequest postBook(BookRequest bookRequest) {
        Book book = injectBook(bookRequest);
        bookRepository.save(book);
        return bookRequest;
    }

    @Override
    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> getBooks(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdAt"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return bookRepository.findAll(pageable);
    }

    private Book injectBook(BookRequest bookRequest) {
        return Book.builder()
                .bookIsbn13(bookRequest.getBookIsbn13())
                .bookIsbn10(bookRequest.getBookIsbn10())
                .bookTitle(bookRequest.getBookTitle())
                .bookContents(bookRequest.getBookContents())
                .bookDescription(bookRequest.getBookDescription())
                .bookPublisher(bookRequest.getBookPublisher())
                .bookDate(bookRequest.getBookDateTime())
                .bookPrice(bookRequest.getBookPrice())
                .bookSalePrice(bookRequest.getBookSalePrice())
                .bookIsPacked(bookRequest.getBookIsPacked())
                .bookThumbnailUrl(bookRequest.getBookThumbnailUrl())
                .bookStock(bookRequest.getBookStock())
                .build();
    }
}

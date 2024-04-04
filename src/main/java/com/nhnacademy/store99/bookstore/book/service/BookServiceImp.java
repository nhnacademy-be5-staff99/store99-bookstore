package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.BookRequest;
import com.nhnacademy.store99.bookstore.book.repository.BookRepository;
import java.util.Optional;
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
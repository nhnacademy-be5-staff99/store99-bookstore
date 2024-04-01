package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.BookDTO;
import com.nhnacademy.store99.bookstore.book.repository.BookRepositoryInterface;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImp implements BookServiceInterface {
    final private BookRepositoryInterface bookRepository;

    public BookServiceImp(BookRepositoryInterface bookRepositoryInterface) {
        this.bookRepository = bookRepositoryInterface;
    }

    @Override
    public BookDTO postBook(BookDTO bookDTO) {
        Book book = injectBook(bookDTO);
        bookRepository.save(book);
        return bookDTO;
    }

    @Override
    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    private Book injectBook(BookDTO bookDTO) {
        return Book.builder()
                .bookIsbn13(bookDTO.getBookIsbn13())
                .bookIsbn11(bookDTO.getBookIsbn11())
                .bookTitle(bookDTO.getBookTitle())
                .bookContents(bookDTO.getBookContents())
                .bookDescription(bookDTO.getBookDescription())
                .bookPublisher(bookDTO.getBookPublisher())
                .bookDate(bookDTO.getBookDateTime())
                .bookPrice(bookDTO.getBookPrice())
                .bookSalePrice(bookDTO.getBookSalePrice())
                .bookIsPacked(bookDTO.getBookIsPacked())
                .bookThumbnailUrl(bookDTO.getBookThumbnailUrl())
                .bookStock(bookDTO.getBookStock())
                .build();
    }
}

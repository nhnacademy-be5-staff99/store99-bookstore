package com.nhnacademy.store99.bookstore.book_author.service;

import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.repository.BookAuthorRepository;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorResponse;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class BookAuthorServiceImp implements BookAuthorService {
    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthorServiceImp(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Override
    public BookAuthorResponse getAuthorBook(Long id) {
        BookAuthor bookAuthor = bookAuthorRepository.findById(id).get();
        return BookAuthorResponse.builder()
                .authorName(bookAuthor.getAuthor().getAuthorName())
                .authorId(bookAuthor.getAuthor().getId())
                .bookId(bookAuthor.getBook().getId())
                .build();
    }

    @Override
    public List<BookAuthor> getAuthorByAuthorId(Long id) {
        return bookAuthorRepository.findBookAuthorsByAuthorId(id);
    }
}

package com.nhnacademy.store99.bookstore.book_author.service;

import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.repository.BookAuthorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class BookAuthorServiceImp implements BookAuthorService {
    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthorServiceImp(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    public List<BookAuthor> getAuthorByBookId(Long id) {
        return bookAuthorRepository.findBookAuthorsByAuthorId(id);
    }

    @Override
    public Optional<BookAuthor> getAuthorBook(Long id) {
        return bookAuthorRepository.findById(id);
    }

    @Override
    public List<BookAuthor> getAuthorByAuthorId(Long id) {
        return bookAuthorRepository.findBookAuthorsByAuthorId(id);
    }
}

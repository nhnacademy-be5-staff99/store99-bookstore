package com.nhnacademy.store99.bookstore.book.service.impl;

import com.nhnacademy.store99.bookstore.book.dto.response.SimpleBookResponse;
import com.nhnacademy.store99.bookstore.book.repository.BookRepository;
import com.nhnacademy.store99.bookstore.book.service.BookService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author seunggyu-kim
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<SimpleBookResponse> getSimpleBooks(final Set<Long> bookIds) {
        return bookRepository.findAllByIdInAndDeletedAtNull(bookIds);
    }
}

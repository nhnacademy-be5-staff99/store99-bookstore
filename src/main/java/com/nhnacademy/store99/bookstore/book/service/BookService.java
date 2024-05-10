package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.dto.response.SimpleBookResponse;
import com.nhnacademy.store99.bookstore.book.response.BookResponse;
import java.util.List;
import java.util.Set;

/**
 * @author seunggyu-kim
 * @author yrrho2
 */
public interface BookService {
    List<SimpleBookResponse> getSimpleBooks(final Set<Long> bookIds);

    BookResponse getBookDataById(Long bookId);

    void plusViewCnt(Long bookId);
}

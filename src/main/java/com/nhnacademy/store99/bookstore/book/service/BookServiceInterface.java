package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.response.BookResponse;

/**
 * 도서 서비스 인터페이스
 *
 * @author yrrho2
 */
public interface BookServiceInterface {
    BookResponse getBookDataById(Long bookId);
}

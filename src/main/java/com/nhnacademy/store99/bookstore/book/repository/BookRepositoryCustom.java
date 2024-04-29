package com.nhnacademy.store99.bookstore.book.repository;

import com.nhnacademy.store99.bookstore.book.response.BookRequest;
import org.springframework.stereotype.Component;

/**
 * 커스텀 도서 JPA 레포지토리 인터페이스
 *
 * @author yrrho2
 */
@Component
public interface BookRepositoryCustom {
    BookRequest getBookDataById(Long bookId);
}
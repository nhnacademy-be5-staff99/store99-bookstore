package com.nhnacademy.store99.bookstore.book.repository;

import com.nhnacademy.store99.bookstore.book.dto.response.BookResponse;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author seunggyu-kim
 * @author yrrho2
 */
@NoRepositoryBean
public interface BookRepositoryCustom {
    BookResponse getBookDataById(Long bookId);
}

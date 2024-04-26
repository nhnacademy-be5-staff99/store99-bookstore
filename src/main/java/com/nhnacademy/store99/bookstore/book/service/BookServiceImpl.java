package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.repository.impl.BookRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 도서 서비스 구현체
 *
 * @author yrrho2
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookServiceInterface {
    @Qualifier("bookRepositoryImpl")
    final private BookRepositoryImpl bookRepositoryimpl;
}

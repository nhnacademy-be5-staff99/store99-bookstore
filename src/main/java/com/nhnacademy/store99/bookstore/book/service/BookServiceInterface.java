package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.entity.BookDTO;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface BookServiceInterface {
    public CommonResponse postBook(BookDTO bookDTO);

    public CommonResponse getBook(Long id);
}

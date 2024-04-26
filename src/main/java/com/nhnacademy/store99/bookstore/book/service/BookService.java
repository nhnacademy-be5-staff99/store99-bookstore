package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.dto.response.SimpleBookResponse;
import java.util.List;
import java.util.Set;

/**
 * @author seunggyu-kim
 */
public interface BookService {
    List<SimpleBookResponse> getSimpleBooks(final Set<Long> bookIds);
}

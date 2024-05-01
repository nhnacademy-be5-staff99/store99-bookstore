package com.nhnacademy.store99.bookstore.book.exception;

import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;

/**
 * @author seunggyu-kim
 */
public class BookNotFoundException extends NotFoundException {
    public BookNotFoundException(Long bookId) {
        super(String.format("Book not found (bookId: %d)", bookId));
    }
}

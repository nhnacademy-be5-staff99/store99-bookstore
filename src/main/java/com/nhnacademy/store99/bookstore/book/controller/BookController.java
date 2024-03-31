package com.nhnacademy.store99.bookstore.book.controller;

import com.nhnacademy.store99.bookstore.book.service.BookServiceInterface;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    final private BookServiceInterface bookServiceInterface;

    public BookController(BookServiceInterface bookServiceInterface) {
        this.bookServiceInterface = bookServiceInterface;
    }
}

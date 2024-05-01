package com.nhnacademy.store99.bookstore.book.controller;

import com.nhnacademy.store99.bookstore.book.dto.response.SimpleBookResponse;
import com.nhnacademy.store99.bookstore.book.service.BookService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author seunggyu-kim
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/open/v1/books")
public class BookOpenController {
    private final BookService bookService;

    @GetMapping(value = "/simple")
    public List<SimpleBookResponse> getSimpleBooks(@RequestParam("bookIds") Set<Long> bookIds) {
        return bookService.getSimpleBooks(bookIds);
    }
}

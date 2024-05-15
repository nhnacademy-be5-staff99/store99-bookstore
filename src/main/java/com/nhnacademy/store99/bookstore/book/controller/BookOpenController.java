package com.nhnacademy.store99.bookstore.book.controller;

import com.nhnacademy.store99.bookstore.book.dto.response.BookListElementDTO;
import com.nhnacademy.store99.bookstore.book.dto.response.BookResponse;
import com.nhnacademy.store99.bookstore.book.dto.response.SimpleBookResponse;
import com.nhnacademy.store99.bookstore.book.service.BookService;
import com.nhnacademy.store99.bookstore.book_author.service.BookAuthorService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    final private BookAuthorService bookAuthorService;

    @GetMapping(value = "/simple")
    public List<SimpleBookResponse> getSimpleBooks(@RequestParam("bookIds") Set<Long> bookIds) {
        return bookService.getSimpleBooks(bookIds);
    }

    @GetMapping("")
    public Page<BookListElementDTO> getBooksFinal(Pageable pageable) {
        return bookAuthorService.getBookListElementsDTO(pageable);
    }

    @GetMapping("/{bookId}")
    public BookResponse getBook(@PathVariable("bookId") Long bookId) {
        BookResponse bookDataById = bookService.getBookDataById(bookId);
        bookService.plusViewCnt(bookId);
        return bookDataById;
    }

}

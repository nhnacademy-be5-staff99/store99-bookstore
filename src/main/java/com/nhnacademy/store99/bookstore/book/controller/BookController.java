package com.nhnacademy.store99.bookstore.book.controller;

import com.nhnacademy.store99.bookstore.book.dto.response.BookListElementDTO;
import com.nhnacademy.store99.bookstore.book.dto.response.BookResponse;
import com.nhnacademy.store99.bookstore.book.service.BookService;
import com.nhnacademy.store99.bookstore.book_author.service.BookAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 도서 전반의 URL 컨트롤러
 *
 * @author yrrho2
 */
@RestController
@RequestMapping("/open/v1/books")
@RequiredArgsConstructor
public class BookController {
    final private BookService bookService;
    final private BookAuthorService bookAuthorService;


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

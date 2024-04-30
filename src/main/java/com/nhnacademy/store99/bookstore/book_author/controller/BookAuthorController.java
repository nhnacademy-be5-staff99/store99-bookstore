package com.nhnacademy.store99.bookstore.book_author.controller;

import com.nhnacademy.store99.bookstore.book_author.service.BookAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 도서의 작가를 참조하는 컨트롤러입니다.
 *
 * @author yrrho2
 */
@RestController
@RequestMapping("/open/v1/books/author")
@RequiredArgsConstructor
public class BookAuthorController {
    final private BookAuthorService bookAuthorService;
}

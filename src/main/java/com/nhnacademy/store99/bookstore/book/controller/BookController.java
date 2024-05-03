package com.nhnacademy.store99.bookstore.book.controller;

import com.nhnacademy.store99.bookstore.book.response.BookResponse;
import com.nhnacademy.store99.bookstore.book.service.BookService;
import com.nhnacademy.store99.bookstore.book_author.response.BookTransDTO;
import com.nhnacademy.store99.bookstore.book_author.service.BookAuthorService;
import com.nhnacademy.store99.bookstore.book_category.response.CategoryParentsDTO;
import com.nhnacademy.store99.bookstore.book_category.service.BookCategoryService;
import java.util.List;
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
    final private BookCategoryService bookCategoryService;

    @GetMapping("")
    public Page<BookTransDTO> getBooksFinal(Pageable pageable) {
        return bookAuthorService.getBookTransDTO(pageable);
    }

    @GetMapping("/{bookId}")
    public BookResponse getBook(@PathVariable("bookId") Long bookId) {
        return bookService.getBookDataById(bookId);
    }

    @GetMapping("/c/{categoryId}")
    public List<CategoryParentsDTO> getCB(@PathVariable("categoryId") Long categoryId) {
        return bookCategoryService.getBooksByCategory(categoryId);
    }

}

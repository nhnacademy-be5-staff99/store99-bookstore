package com.nhnacademy.store99.bookstore.book.controller;

import com.nhnacademy.store99.bookstore.book.entity.BookDTO;
import com.nhnacademy.store99.bookstore.book.service.BookServiceInterface;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    final private BookServiceInterface bookService;

    public BookController(BookServiceInterface bookServiceInterface) {
        this.bookService = bookServiceInterface;
    }

    @GetMapping("/{bookId}")
    public CommonResponse getBook(@PathVariable("bookId") Long id) {
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .build();
        return CommonResponse.builder()
                .header(commonHeader)
                .result(bookService.getBook(id).get())
                .build();


    }

    @PostMapping
    public CommonResponse postBook(@RequestBody BookDTO bookDTO) {
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK).build();
        return CommonResponse.builder()
                .header(commonHeader)
                .result(bookService.postBook(bookDTO))
                .build();
    }

}

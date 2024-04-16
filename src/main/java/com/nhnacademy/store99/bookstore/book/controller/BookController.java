package com.nhnacademy.store99.bookstore.book.controller;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.BookRequest;
import com.nhnacademy.store99.bookstore.book.service.BookServiceInterface;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open/v1/books")
public class BookController {
    final private BookServiceInterface bookService;

    public BookController(BookServiceInterface bookServiceInterface) {
        this.bookService = bookServiceInterface;
    }


    @GetMapping("")
    public ResponseEntity<CommonResponse> getBooksPage(@RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Book> paging = this.bookService.getBooks(page);
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .build();
        CommonResponse commonResponse = CommonResponse.builder()
                .header(commonHeader)
                .result(paging)
                .build();
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<CommonResponse> getBook(@PathVariable("bookId") Long id) {
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .build();
        CommonResponse commonResponse = CommonResponse.builder()
                .header(commonHeader)
                .result(bookService.getBook(id).get())
                .build();
        return ResponseEntity.ok(commonResponse);
    }

    @PostMapping
    public ResponseEntity<CommonResponse> postBook(@RequestBody BookRequest bookRequest) {
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK).build();
        CommonResponse commonResponse = CommonResponse.builder()
                .header(commonHeader)
                .result(bookService.postBook(bookRequest))
                .build();
        return ResponseEntity.ok(commonResponse);
    }

}

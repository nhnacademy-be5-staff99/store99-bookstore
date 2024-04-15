package com.nhnacademy.store99.bookstore.book_author.controller;

import com.nhnacademy.store99.bookstore.book_author.service.BookAuthorService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/books/author")
public class BookAuthorController {
    final private BookAuthorService bookAuthorService;

    public BookAuthorController(BookAuthorService bookAuthorService) {
        this.bookAuthorService = bookAuthorService;
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<CommonResponse> getBookAuthorbyAuthorId(@PathVariable("authorId") Long id) {
        CommonHeader commonHeader = CommonHeader.builder().httpStatus(HttpStatus.OK).build();
        CommonResponse commonResponse = CommonResponse.builder()
                .header(commonHeader)
                .result(bookAuthorService.getAuthorByAuthorId(id))
                .build();
        return ResponseEntity.ok(commonResponse);
    }


    @GetMapping("/book/{bookId}")
    public ResponseEntity<CommonResponse> getBookAuthor(@PathVariable("bookId") Long id) {
        CommonHeader commonHeader = CommonHeader.builder().httpStatus(HttpStatus.OK).build();
        CommonResponse commonResponse = CommonResponse.builder()
                .header(commonHeader)
                .result(bookAuthorService.getAuthorBook(id).get())
                .build();
        return ResponseEntity.ok(commonResponse);
    }

}

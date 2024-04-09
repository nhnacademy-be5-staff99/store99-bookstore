package com.nhnacademy.store99.bookstore.book.controller;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.BookRequest;
import com.nhnacademy.store99.bookstore.book.service.BookServiceInterface;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonListResponse;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    final private BookServiceInterface bookService;

    public BookController(BookServiceInterface bookServiceInterface) {
        this.bookService = bookServiceInterface;
    }

    @GetMapping("")
    public ResponseEntity<CommonListResponse> getBooks() {
        List<Book> bookRequestList = bookService.getBooks();
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .build();
        CommonListResponse commonListResponse = CommonListResponse.builder()
                .header(commonHeader)
                .resultList(new ArrayList<>(bookRequestList))
                .totalCount(bookRequestList.size())
                .build();
        return ResponseEntity.ok(commonListResponse);
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

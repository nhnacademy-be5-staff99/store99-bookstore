package com.nhnacademy.store99.bookstore.book_author.controller;

import com.nhnacademy.store99.bookstore.book_author.service.BookAuthorService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 도서의 작가를 참조하는 컨트롤러입니다.
 *
 * @author yrrho2
 */
@RestController
@RequestMapping("/open/v1/books/author")
public class BookAuthorController {
    final private BookAuthorService bookAuthorService;

    public BookAuthorController(BookAuthorService bookAuthorService) {
        this.bookAuthorService = bookAuthorService;
    }

    /**
     * 작가가 작성한 책들을 반환합니다.
     *
     * @param authorId 작가의 id
     * @return 검색된 도서들의 List를 반환합니다.
     */
    @GetMapping("/{authorId}")
    public ResponseEntity<CommonResponse> getBookAuthorbyAuthorId(@PathVariable("authorId") Long authorId) {
        CommonHeader commonHeader = CommonHeader.builder().httpStatus(HttpStatus.OK).build();
        CommonResponse commonResponse = CommonResponse.builder()
                .header(commonHeader)
                .result(bookAuthorService.getAuthorByAuthorId(authorId))
                .build();
        return ResponseEntity.ok(commonResponse);
    }


    /**
     * 도서 id를 받아 작가를 참조합니다
     *
     * @param bookId 도서Id
     * @return 검색된 작가의 id가 담긴 BookAuthorResponse를 반환합니다
     */
    @GetMapping("/book")
    public ResponseEntity<CommonResponse> getBookAuthor(@RequestParam("bookId") Long bookId) {
        CommonHeader commonHeader = CommonHeader.builder().httpStatus(HttpStatus.OK).build();
        CommonResponse commonResponse = CommonResponse.builder()
                .header(commonHeader)
                .result(bookAuthorService.getAuthorBook(bookId))
                .build();
        return ResponseEntity.ok(commonResponse);
    }

}

package com.nhnacademy.store99.bookstore.book.controller;

import com.nhnacademy.store99.bookstore.book.response.BookRequest;
import com.nhnacademy.store99.bookstore.book_author.response.BookTransDTO;
import com.nhnacademy.store99.bookstore.book_author.service.BookAuthorService;
import com.nhnacademy.store99.bookstore.book_image.repository.impl.BookImageRepositoryImpl;
import com.nhnacademy.store99.bookstore.book_image.response.BookImageDTO;
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
    final private BookAuthorService bookAuthorService;
    final private BookImageRepositoryImpl bookImageRepository;


    @GetMapping("")
    public Page<BookTransDTO> getBooksFinal(Pageable pageable) {
        return bookAuthorService.getBookTransDTO(pageable);
    }

    @GetMapping("/{bookId}")
    public BookRequest getBook(@PathVariable("bookId") Long bookId) {
//        return null;
//        List<BookRequest.AuthorDTO> bookAuthorResponses = bookAuthorService.getAuthorBook(bookId);
        BookImageDTO bookRequest = bookImageRepository.getBookImageData(bookId);

        return bookRequest;
    }

}

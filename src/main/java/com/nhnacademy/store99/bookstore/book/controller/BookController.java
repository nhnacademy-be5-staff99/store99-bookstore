package com.nhnacademy.store99.bookstore.book.controller;

import com.nhnacademy.store99.bookstore.book.repository.impl.BookRepositoryImpl;
import com.nhnacademy.store99.bookstore.book.response.BookRequest;
import com.nhnacademy.store99.bookstore.book_author.response.BookTransDTO;
import com.nhnacademy.store99.bookstore.book_author.service.BookAuthorService;
import com.nhnacademy.store99.bookstore.book_image.repository.impl.BookImageRepositoryImpl;
import com.nhnacademy.store99.bookstore.book_image.response.BookImageDTO;
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
    final private BookAuthorService bookAuthorService;
    final private BookImageRepositoryImpl bookImageRepository;
    final private BookRepositoryImpl bookRepository;


    @GetMapping("")
    public Page<BookTransDTO> getBooksFinal(Pageable pageable) {
        return bookAuthorService.getBookTransDTO(pageable);
    }

    @GetMapping("/{bookId}")
    public BookRequest getBook(@PathVariable("bookId") Long bookId) {

        // 도서-작가 리스트객체 받아오기
        List<BookRequest.AuthorDTO> bookAuthorResponses = bookAuthorService.getAuthorBook(bookId);

        // 도서-이미지 DTO받오기
        BookImageDTO bookImageDTO = bookImageRepository.getBookImageData(bookId);

        // BookRequest 도서 기타 정보 받아오기
        BookRequest bookRequest = bookRepository.getBookDataById(bookId);

        bookRequest.setBookId(bookId);
        bookRequest.setAuthorsDTOList(bookAuthorResponses);
        bookRequest.setBookImageName(bookImageDTO.getBookImageName());
        bookRequest.setBookImageURL(bookImageDTO.getBookImageURL());

        return bookRequest;
    }

}

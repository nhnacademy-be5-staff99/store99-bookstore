package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.repository.impl.BookRepositoryImpl;
import com.nhnacademy.store99.bookstore.book.response.BookResponse;
import com.nhnacademy.store99.bookstore.book_author.service.BookAuthorService;
import com.nhnacademy.store99.bookstore.book_image.response.BookImageDTO;
import com.nhnacademy.store99.bookstore.book_image.service.BookImageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 도서 서비스 구현체
 *
 * @author yrrho2
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookServiceInterface {
    @Qualifier("bookRepositoryImpl")
    final private BookRepositoryImpl bookRepositoryimpl;

    final private BookAuthorService bookAuthorService;
    final private BookImageService bookImageService;

    @Override
    public BookResponse getBookDataById(Long bookId) {

        // 도서-작가 리스트객체 받아오기
        List<BookResponse.AuthorDTO> bookAuthorResponses = bookAuthorService.getAuthorBook(bookId);

        // 도서-이미지 DTO받오기
        BookImageDTO bookImageDTO = bookImageService.getBookImageData(bookId);

        // BookRequest 도서 기타 정보 받아오기
        BookResponse bookRequest = bookRepositoryimpl.getBookDataById(bookId);

        bookRequest.setBookId(bookId);
        bookRequest.setAuthorsDTOList(bookAuthorResponses);
        bookRequest.setBookImageName(bookImageDTO.getBookImageName());
        bookRequest.setBookImageURL(bookImageDTO.getBookImageURL());


        return bookRequest;
    }
}

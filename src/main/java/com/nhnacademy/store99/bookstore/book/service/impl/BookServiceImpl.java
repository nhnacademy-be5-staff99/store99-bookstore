package com.nhnacademy.store99.bookstore.book.service.impl;

import com.nhnacademy.store99.bookstore.book.dto.response.SimpleBookResponse;
import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.repository.BookJPARepository;
import com.nhnacademy.store99.bookstore.book.repository.BookRepository;
import com.nhnacademy.store99.bookstore.book.response.BookResponse;
import com.nhnacademy.store99.bookstore.book.service.BookService;
import com.nhnacademy.store99.bookstore.book_author.service.BookAuthorService;
import com.nhnacademy.store99.bookstore.book_image.response.BookImageDTO;
import com.nhnacademy.store99.bookstore.book_image.service.BookImageService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author seunggyu-kim
 * @author yrrho2
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class BookServiceImpl implements BookService {
    private final BookJPARepository bookJPARepository;
    private final BookRepository bookRepository;
    private final BookAuthorService bookAuthorService;
    private final BookImageService bookImageService;

    @Override
    public List<SimpleBookResponse> getSimpleBooks(final Set<Long> bookIds) {
        return bookJPARepository.findAllByIdInAndDeletedAtNull(bookIds);
    }

    @Override
    public BookResponse getBookDataById(Long bookId) {

        // 도서-작가 리스트객체 받아오기
        List<BookResponse.AuthorDTO> bookAuthorResponses = bookAuthorService.getAuthorBook(bookId);

        // 도서-이미지 DTO받오기
        BookImageDTO bookImageDTO = bookImageService.getBookImageData(bookId);

        // BookRequest 도서 기타 정보 받아오기
        BookResponse bookRequest = bookRepository.getBookDataById(bookId);

        bookRequest.setBookId(bookId);
        bookRequest.setAuthorsDTOList(bookAuthorResponses);
        bookRequest.setBookImageName(bookImageDTO.getBookImageName());
        bookRequest.setBookImageURL(bookImageDTO.getBookImageURL());
        plusViewCnt(bookId);
        return bookRequest;
    }

    private void plusViewCnt(Long bookId) {
        bookJPARepository.findById(bookId).ifPresent(Book::plusViewCnt);
    }
}

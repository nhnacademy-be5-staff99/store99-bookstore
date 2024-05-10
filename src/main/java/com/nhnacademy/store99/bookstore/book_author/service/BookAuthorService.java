package com.nhnacademy.store99.bookstore.book_author.service;

import com.nhnacademy.store99.bookstore.book.dto.response.response.BookListElementDTO;
import com.nhnacademy.store99.bookstore.book.dto.response.response.BookResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 도서 작가 인터페이스
 *
 * @author yrrho2
 */
public interface BookAuthorService {

    Page<BookListElementDTO> getBookListElementsDTO(Pageable pageable);

    List<BookResponse.AuthorDTO> getAuthorBook(Long id);


}

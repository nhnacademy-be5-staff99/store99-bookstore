package com.nhnacademy.store99.bookstore.book_author.service;

import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorResponse;
import com.nhnacademy.store99.bookstore.book_author.response.BookTransDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 도서 작가 인터페이스
 *
 * @author yrrho2
 */
public interface BookAuthorService {

    Page<BookTransDTO> getBookTransDTO(Pageable pageable);

    List<BookAuthor> getAuthorByAuthorId(Long id);

    List<BookAuthorResponse> getAuthorBook(Long id);


}

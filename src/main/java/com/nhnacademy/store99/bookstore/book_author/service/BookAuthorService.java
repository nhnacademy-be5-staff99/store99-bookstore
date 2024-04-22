package com.nhnacademy.store99.bookstore.book_author.service;

import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorAPIResponse;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorDTO;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorName;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorResponse;
import com.nhnacademy.store99.bookstore.book_author.response.BookPageDTO;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 * 도서 작가 인터페이스
 *
 * @author yrrho2
 */
public interface BookAuthorService {

    List<BookAuthor> getAuthorByAuthorId(Long id);

    BookAuthorResponse getAuthorBook(Long id);

    List<BookAuthorDTO> getBookAuthorsByIdGreaterThan(Long id);

    List<BookAuthorAPIResponse> getBookAuthorByBooks();

    List<BookAuthorName> getSameIdBookAuthor();

    Page<BookPageDTO> getBooksAuthorName();

}

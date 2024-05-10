package com.nhnacademy.store99.bookstore.book_author.repository;

import com.nhnacademy.store99.bookstore.book.dto.response.BookListElementDTO;
import com.nhnacademy.store99.bookstore.book.dto.response.BookResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 도서 작가 레포지토리
 *
 * @author yrrho2
 */
public interface BookAuthorRepositoryCustom {

    // 도서 DTO + 작가 이름,역할, 삭제==null
    // 위와같은 쿼리를 가진 최종 응답을 만들자
    // 이거 Long id 뺴고 이름 바꿔야함.
    public Page<BookListElementDTO> findBooks(Pageable pageable);

    List<BookResponse.AuthorDTO> getAuthorsById(Long bookId);
}

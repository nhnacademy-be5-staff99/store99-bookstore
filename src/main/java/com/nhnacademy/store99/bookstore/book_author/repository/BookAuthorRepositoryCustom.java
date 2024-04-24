package com.nhnacademy.store99.bookstore.book_author.repository;

import com.nhnacademy.store99.bookstore.book.response.BookWithAuthor;
import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorAPIResponse;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorName;
import com.nhnacademy.store99.bookstore.book_author.response.BookTransDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

/**
 * 도서 작가 레포지토리
 *
 * @author yrrho2
 */
public interface BookAuthorRepositoryCustom {

    // 도서 DTO + 작가 이름,역할, 삭제==null
    // 위와같은 쿼리를 가진 최종 응답을 만들자

    Page<BookTransDTO> findBooksByIdGreaterThan(Long id, Pageable pageable);


    List<BookWithAuthor> findBooksByIdGreaterThanEqual(Long id);

    // BookAuthor 테이블에 연결된 작가-도서 데이터 출력.
    // 작가가 작성한 도서들을 반환함.
    List<BookAuthorAPIResponse> findBookAuthorsByIdGreaterThan(Long id);


    // 메소드 쿼리를 실행하고
    // 그 값을 레포지토리 구현채에서 사용하는듯
    List<BookAuthorName> findBookAuthorByBookId(Long id);

    // 이 기능은 QueryDSL 테스트를 위함.
    @EntityGraph(attributePaths = {"book", "author"})
    List<BookAuthor> getBookAuthorsByIdGreaterThan(Long id);
}

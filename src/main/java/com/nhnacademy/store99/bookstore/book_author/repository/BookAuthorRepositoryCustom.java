package com.nhnacademy.store99.bookstore.book_author.repository;

import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorAPIResponse;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorName;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 도서 작가 레포지토리
 *
 * @author yrrho2
 */
@NoRepositoryBean
public interface BookAuthorRepositoryCustom {


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

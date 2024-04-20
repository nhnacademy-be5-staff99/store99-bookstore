package com.nhnacademy.store99.bookstore.book_author.repository;

import com.nhnacademy.store99.bookstore.author.entity.Author;
import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorAPIResponse;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorName;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 도서 작가 레포지토리
 *
 * @author yrrho2
 */
@NoRepositoryBean
public interface BookAuthorRepositoryCustom {


    // 임시로 모든 데이터 출력. 나중에 도서제목검색결과에 작가 이름을 넣는식으로 해볼것.
//    @Query(value = "SELECT distinct b as book, a.authorName as authorName " +
//            "FROM Book as b inner join fetch BookAuthor as ba inner join fetch Author as a " +
//            "WHERE ba.book.id = b.id AND ba.author.id = a.id")
    List<BookAuthorAPIResponse> findBookAuthorsByIdGreaterThan(Long id);


    // 메소드 쿼리를 실행하고
    // 그 값을 레포지토리 구현채에서 사용하는건가????
    List<BookAuthorName> findBookAuthorByBookId(Long id);

    // getItemsHavingOrderItemQuantityGreaterThan
    // 이 기능은 QueryDSL 테스트를 위함.
    @EntityGraph(attributePaths = {"book", "author"})
    List<BookAuthor> getBookAuthorsByIdGreaterThan(Long id);

    @Query("SELECT a  FROM Author a")
    List<Author> testQDLS();
}

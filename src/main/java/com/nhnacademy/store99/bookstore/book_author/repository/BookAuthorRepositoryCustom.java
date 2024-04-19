package com.nhnacademy.store99.bookstore.book_author.repository;

import com.nhnacademy.store99.bookstore.author.entity.Author;
import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
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
    List<BookAuthor> findBookAuthorsByAuthorId(Long id);


    // 임시로 모든 데이터 출력. 나중에 도서제목검색결과에 작가 이름을 넣는식으로 해볼것.
//    @Query(value = "SELECT distinct b as book, a.authorName as authorName " +
//            "FROM Book as b inner join fetch BookAuthor as ba inner join fetch Author as a " +
//            "WHERE ba.book.id = b.id AND ba.author.id = a.id")

    // getItemsHavingOrderItemQuantityGreaterThan
    @EntityGraph(attributePaths = {"book", "author"})
    List<BookAuthor> getBookAuthorsByIdGreaterThan(Long id);

    @Query("SELECT a  FROM Author a")
    List<Author> testQDLS();
}

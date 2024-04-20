package com.nhnacademy.store99.bookstore.book.repository;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * 도서 JPA 레포지토리 인터페이스
 *
 * @author yrrho2
 */
@NoRepositoryBean
public interface BookRepositoryCustom {
    // 임시로 모든 데이터 출력. 나중에 도서제목검색결과에 작가 이름을 넣는식으로 해볼것.
//    @Query(value = "SELECT distinct b as book, a.authorName as authorName " +
//            "FROM Book as b inner join fetch BookAuthor as ba inner join fetch Author as a " +
//            "WHERE ba.book.id = b.id AND ba.author.id = a.id")

    //List<Book> getBooksByBook();
}

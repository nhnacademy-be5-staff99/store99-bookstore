package com.nhnacademy.store99.bookstore.book_author.repository;

import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 도서 작가 레포지토리
 *
 * @author yrrho2
 */
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long>, BookAuthorRepositoryCustom {

    // Named Query 메소드 테스트용.
    List<BookAuthor> findBookAuthorsByAuthorId(Long id);
}

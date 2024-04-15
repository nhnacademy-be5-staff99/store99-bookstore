package com.nhnacademy.store99.bookstore.book_author.repository;

import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 도서 작가 레포지토리
 *
 * @author yrrho2
 */
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
    List<BookAuthor> findBookAuthorsByAuthorId(Long id);
}

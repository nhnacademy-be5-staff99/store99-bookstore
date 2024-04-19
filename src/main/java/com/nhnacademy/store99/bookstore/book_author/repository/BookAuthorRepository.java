package com.nhnacademy.store99.bookstore.book_author.repository;

import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 도서 작가 레포지토리
 *
 * @author yrrho2
 */
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long>, BookAuthorRepositoryCustom {

}

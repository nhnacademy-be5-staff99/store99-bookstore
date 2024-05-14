package com.nhnacademy.store99.bookstore.book.repository;

import com.nhnacademy.store99.bookstore.book.dto.response.SimpleBookResponse;
import com.nhnacademy.store99.bookstore.book.entity.Book;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 커스텀 도서 JPA 레포지토리 인터페이스
 *
 * @author yrrho2
 * @author seunggyu-kim
 */
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
    List<SimpleBookResponse> findAllByIdInAndDeletedAtNull(Set<Long> bookIds);
}

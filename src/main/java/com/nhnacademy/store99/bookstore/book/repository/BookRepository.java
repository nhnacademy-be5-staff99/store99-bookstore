package com.nhnacademy.store99.bookstore.book.repository;

import com.nhnacademy.store99.bookstore.book.dto.response.SimpleBookResponse;
import com.nhnacademy.store99.bookstore.book.entity.Book;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
    List<SimpleBookResponse> findAllByIdInAndDeletedAtNull(Set<Long> bookIds);

}

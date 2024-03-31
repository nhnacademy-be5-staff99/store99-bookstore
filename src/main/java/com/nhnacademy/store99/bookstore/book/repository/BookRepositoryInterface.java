package com.nhnacademy.store99.bookstore.book.repository;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryInterface extends JpaRepository<Book, Long> {
}

package com.nhnacademy.store99.bookstore.book.repository.imp;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.repository.BookRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * 도서 QueryDSL 레포지토리 구현체 <br>
 *
 * @author yrrho2
 */
public class BookRepositoryImp extends QuerydslRepositorySupport implements BookRepositoryCustom {
    public BookRepositoryImp() {
        super(Book.class);
    }

}

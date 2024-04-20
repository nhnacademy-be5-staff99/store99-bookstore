package com.nhnacademy.store99.bookstore.book.repository.imp;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.repository.BookRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BookRepositoryImp extends QuerydslRepositorySupport implements BookRepositoryCustom {
    public BookRepositoryImp() {
        super(Book.class);
    }

//    @Override
//    public List<Book> getBooksByBook() {
//        return null;
//    }


}

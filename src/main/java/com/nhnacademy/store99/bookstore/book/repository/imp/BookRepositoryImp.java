package com.nhnacademy.store99.bookstore.book.repository.imp;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.repository.BookRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BookRepositoryImp extends QuerydslRepositorySupport implements BookRepositoryCustom {
    public BookRepositoryImp() {
        super(Book.class);
    }


//    @Override
//    public List<BookWithAuthor> findBooksByIdGreaterThanEqual(Long id) {
//        QBook book = QBook.book;
//        QAuthor author = QAuthor.author;
//        QBookAuthor bookAuthor = QBookAuthor.bookAuthor;
//        // book과 Author List를 각각 다른 쿼리로 실행해서 DTO로 합쳐보자
////        List<Book> queryBook = from(book)
////                .select(book).fetch();
//        return from(book)
//                .join(book, bookAuthor.book)
//                .join(bookAuthor.author, author)
//                .where(book.id.eq(bookAuthor.book.id))
//                .where(bookAuthor.author.id.eq(author.id))
//                .select(Projections.bean(BookWithAuthor.class,
//                        book, bookAuthor.author)).distinct().fetch();
//    }


}

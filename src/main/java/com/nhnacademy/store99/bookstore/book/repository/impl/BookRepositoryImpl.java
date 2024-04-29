package com.nhnacademy.store99.bookstore.book.repository.impl;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book.repository.BookRepositoryCustom;
import com.nhnacademy.store99.bookstore.book.response.BookRequest;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

/**
 * 도서 QueryDSL 레포지토리 구현체 <br>
 *
 * @author yrrho2
 */
@Component
public class BookRepositoryImpl extends QuerydslRepositorySupport implements BookRepositoryCustom {
    public BookRepositoryImpl() {
        super(Book.class);
    }

    @Override
    public BookRequest getBookDataById(Long bookId) {
        QBook book = QBook.book;
        return from(book)
                .where(book.id.eq(bookId))
                .select(Projections.bean(BookRequest.class,
                        book.bookIsbn13,
                        book.bookIsbn10,
                        book.bookTitle,
                        book.bookContents,
                        book.bookPublisher,
                        book.bookDate,
                        book.bookPrice,
                        book.bookSalePrice,
                        book.bookIsPacked,
                        book.bookThumbnailUrl,
                        book.bookStock,
                        book.bookCntOfReview,
                        book.bookAvgOfRate,
                        book.createdAt,
                        book.updatedAt
                )).fetchOne();
    }
}

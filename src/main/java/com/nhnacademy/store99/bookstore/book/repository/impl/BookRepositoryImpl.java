package com.nhnacademy.store99.bookstore.book.repository.impl;

import com.nhnacademy.store99.bookstore.book.dto.response.BookResponse;
import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book.repository.BookRepositoryCustom;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

/**
 * 도서 QueryDSL 레포지토리 구현체 <br>
 *
 * @author yrrho2
 * @author seunggyu-kim
 */
@Component
public class BookRepositoryImpl extends QuerydslRepositorySupport implements BookRepositoryCustom {
    public BookRepositoryImpl() {
        super(Book.class);
    }

    @Override
    public BookResponse getBookDataById(Long bookId) {
        QBook book = QBook.book;
        return from(book)
                .where(book.id.eq(bookId))
                .where(book.deletedAt.isNull())
                .select(Projections.bean(BookResponse.class,
                        book.id.as("bookId"),
                        book.bookIsbn13,
                        book.bookIsbn10,
                        book.bookTitle,
                        book.bookContents,
                        book.bookDescription,
                        book.bookPublisher,
                        book.bookDate,
                        book.bookPrice,
                        book.bookSalePrice,
                        book.bookThumbnailUrl,
                        book.bookStock,
                        book.bookCntOfReview,
                        book.bookAvgOfRate,
                        book.createdAt,
                        book.updatedAt
                )).fetchOne();
    }
}

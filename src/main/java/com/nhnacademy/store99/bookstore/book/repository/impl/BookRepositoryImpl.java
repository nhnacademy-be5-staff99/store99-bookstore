package com.nhnacademy.store99.bookstore.book.repository.impl;

import com.nhnacademy.store99.bookstore.author.entity.QAuthor;
import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book.repository.BookRepositoryCustom;
import com.nhnacademy.store99.bookstore.book.response.BookRequest;
import com.nhnacademy.store99.bookstore.book_author.entity.QBookAuthor;
import com.nhnacademy.store99.bookstore.book_author.response.BookTransDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
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
        QBookAuthor bookAuthor = QBookAuthor.bookAuthor;
        QAuthor author = QAuthor.author;

        JPQLQuery<BookTransDTO.AuthorDTO> authorsDTOList = from(bookAuthor)
                .where(bookAuthor.book.id.in(bookId))
                .join(bookAuthor.author, author)
                .select(
                        Projections.constructor(BookTransDTO.AuthorDTO.class,
                                author.authorName, author.authorType)
                );
        return from(book)
                .where(book.id.eq(bookId))
                .select(Projections.bean(BookRequest.class,
                        book.id,
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
                        book.updatedAt,
                        authorsDTOList
                )).fetchOne();
    }
}

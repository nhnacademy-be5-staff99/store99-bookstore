package com.nhnacademy.store99.bookstore.book_author.repository.impl;

import com.nhnacademy.store99.bookstore.author.entity.QAuthor;
import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.entity.QBookAuthor;
import com.nhnacademy.store99.bookstore.book_author.repository.BookAuthorRepositoryCustom;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorResponse;
import com.nhnacademy.store99.bookstore.book_author.response.BookTransDTO;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

/**
 * <h2>도서-작가 레포지토리 인터페이스 구현체 </h2>
 * 인터페이스의 Named Query 메소드 실행후 결과값을 <br>
 * Query DSL을 사용하여 데이터 반환.
 *
 * @author yrrho2
 */
@Component
public class BookAuthorRepositoryImpl extends QuerydslRepositorySupport implements BookAuthorRepositoryCustom {
    public BookAuthorRepositoryImpl() {
        super(BookAuthor.class);
    }

    @Override
    public Page<BookTransDTO> findBooksByIdGreaterThan(Long id, Pageable pageable) {
        QBook book = QBook.book;
        QAuthor author = QAuthor.author;
        QBookAuthor bookAuthor = QBookAuthor.bookAuthor;

        JPQLQuery<Book> bookQuery = from(book)
                .leftJoin(bookAuthor).on(book.id.eq(bookAuthor.book.id))
                .leftJoin(author).on(bookAuthor.author.id.eq(author.id))
                .where(bookAuthor.book.id.eq(book.id))
                .where(bookAuthor.author.id.eq(author.id)).distinct();

        List<Book> books = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, bookQuery).fetch();
        List<Long> bookIds = books.stream().map(Book::getId).collect(Collectors.toList());


        Map<Long, List<BookTransDTO.AuthorDTO>> authorsMap = from(bookAuthor)
                .where(bookAuthor.book.id.in(bookIds))
                .join(bookAuthor.author, author)
                .transform(GroupBy.groupBy(bookAuthor.book.id)
                        .as(GroupBy.list(
                                        Projections.constructor(BookTransDTO.AuthorDTO.class,
                                                author.authorName, author.authorType)
                                )
                        )
                );

        List<BookTransDTO> bookResponse = books.stream().map(b -> {
            List<BookTransDTO.AuthorDTO> authors = authorsMap.getOrDefault(b.getId(), Collections.emptyList());
            return new BookTransDTO(
                    b.getId(),
                    b.getBookIsbn13(),
                    b.getBookIsbn10(),
                    b.getBookTitle(),
                    b.getBookContents(),
                    b.getBookPublisher(),
                    b.getBookDate(),
                    b.getBookPrice(),
                    b.getBookSalePrice(),
                    b.getBookIsPacked(),
                    b.getBookThumbnailUrl(),
                    b.getBookStock(),
                    b.getBookCntOfReview(),
                    b.getBookAvgOfRate(),
                    b.getCreatedAt(),
                    b.getUpdatedAt(),
                    authors
            );
        }).collect(Collectors.toList());


        long fetchCount = bookQuery.fetchCount();
        return new PageImpl<>(bookResponse, pageable, fetchCount);
    }

    @Override
    public List<BookAuthorResponse> getAuthorsById(Long bookId) {
        QBook book = QBook.book;
        QBookAuthor bookAuthor = QBookAuthor.bookAuthor;
        QAuthor author = QAuthor.author;

        List<BookTransDTO.AuthorDTO> authorDTOList = from(bookAuthor)
                .where(bookAuthor.book.id.in(bookId))
                .join(bookAuthor.author, author)
                .select(
                        Projections.constructor(BookTransDTO.AuthorDTO.class,
                                author.authorName, author.authorType)
                ).fetch();


        return from(bookAuthor)
                .where(bookAuthor.book.id.eq(bookId))
                .where(bookAuthor.book.id.eq(book.id))
                .where(bookAuthor.author.id.eq(author.id))
                .leftJoin(bookAuthor.book, book)
                .leftJoin(bookAuthor.author, author)
                .select(
                        Projections.constructor(BookAuthorResponse.class,
                                author.authorName,
                                author.authorType
                        )
                ).fetch();
    }
}

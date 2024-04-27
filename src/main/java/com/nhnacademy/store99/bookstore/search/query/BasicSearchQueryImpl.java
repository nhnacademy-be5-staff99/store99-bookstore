package com.nhnacademy.store99.bookstore.search.query;

import com.nhnacademy.store99.bookstore.author.entity.QAuthor;
import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.entity.QBookAuthor;
import com.nhnacademy.store99.bookstore.search.dto.BasicSearchResponse;
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
 * 도서 검색 쿼리
 *
 * @author Ahyeon Song
 */
@Component
public class BasicSearchQueryImpl extends QuerydslRepositorySupport implements BasicSearchQuery {

    public BasicSearchQueryImpl() {
        super(BookAuthor.class);
    }

    /**
     * contains(like 절) 을 이용해 도서 제목이나 저자에 content 가 포함된 도서 리스트 페이징하여 반환
     */
    @Override
    public Page<BasicSearchResponse> getSearchResult(String content, Pageable pageable) {
        QBook book = QBook.book;
        QAuthor author = QAuthor.author;
        QBookAuthor bookAuthor = QBookAuthor.bookAuthor;

        // 도서 기본 정보 조회
        JPQLQuery<Book> bookQuery = from(book)
                .leftJoin(bookAuthor).on(book.id.eq(bookAuthor.book.id))
                .leftJoin(author).on(bookAuthor.author.id.eq(author.id))
                .where(book.bookTitle.contains(content)
                        .or(author.authorName.contains(content)))
                .groupBy(book.id);

        List<Book> books = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, bookQuery).fetch();
        List<Long> bookIds = books.stream().map(Book::getId).collect(Collectors.toList());

        // 저자 정보 조회
        Map<Long, List<BasicSearchResponse.BookAuthorInfo>> authorsMap = from(bookAuthor)
                .where(bookAuthor.book.id.in(bookIds))
                .join(bookAuthor.author, author)
                .transform(GroupBy.groupBy(bookAuthor.book.id).as(GroupBy.list(
                        Projections.constructor(BasicSearchResponse.BookAuthorInfo.class, author.authorName,
                                author.authorType))));

        // 결과 조합
        List<BasicSearchResponse> searchResponse = books.stream().map(b -> {
            List<BasicSearchResponse.BookAuthorInfo> authorInfos =
                    authorsMap.getOrDefault(b.getId(), Collections.emptyList());
            return new BasicSearchResponse(
                    b.getId(),
                    b.getBookTitle(),
                    b.getBookThumbnailUrl(),
                    authorInfos,
                    b.getBookPublisher(),
                    b.getBookDate(),
                    b.getBookPrice(),
                    b.getBookSalePrice(),
                    b.getBookCntOfReview()
            );
        }).collect(Collectors.toList());

        long fetchCount = getQuerydsl().applyPagination(pageable, bookQuery).fetchCount();

        return new PageImpl<>(searchResponse, pageable, fetchCount);
    }


}

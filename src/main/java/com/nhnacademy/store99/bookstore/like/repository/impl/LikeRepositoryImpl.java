package com.nhnacademy.store99.bookstore.like.repository.impl;

import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.like.dto.response.BookInfoForLikeResponse;
import com.nhnacademy.store99.bookstore.like.entity.Like;
import com.nhnacademy.store99.bookstore.like.entity.QLike;
import com.nhnacademy.store99.bookstore.like.repository.LikeRepositoryCustom;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class LikeRepositoryImpl extends QuerydslRepositorySupport implements LikeRepositoryCustom {

    public LikeRepositoryImpl() {
        super(Like.class);
    }

    /**
     * 도서 별 좋아요 수를 조회하는 메소드 입니다.
     *
     * @param bookId
     * @return SELECT COUNT(*)
     * FROM like AS l
     * JOIN book AS b
     * ON l.book_id = b.book_id;
     */
    @Override
    public Long countAllByBook(Long bookId) {
        QBook book = QBook.book;
        QLike like = QLike.like;

        return from(like)
                .innerJoin(book).on(like.book.id.eq(book.id))
                .where(like.book.id.eq(bookId))
                .fetchCount();
    }

    @Override
    public List<BookInfoForLikeResponse> getAllByUser(Long userId) {
        QLike like = QLike.like;
        QBook book = QBook.book;

        return from(like)
                .innerJoin(book).on(like.book.eq(book))
                .where(like.user.id.eq(userId))
                .select(Projections.constructor(
                        BookInfoForLikeResponse.class,
                        book.id,
                        book.bookThumbnailUrl,
                        book.bookTitle,
                        book.bookPrice,
                        book.bookSalePrice
                ))
                .fetch();
    }
}

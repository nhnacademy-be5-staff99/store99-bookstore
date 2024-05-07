package com.nhnacademy.store99.bookstore.like.repository.impl;

import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.like.dto.response.BookInfoForLikeResponse;
import com.nhnacademy.store99.bookstore.like.dto.response.QBookInfoForLikeResponse;
import com.nhnacademy.store99.bookstore.like.entity.Like;
import com.nhnacademy.store99.bookstore.like.entity.QLike;
import com.nhnacademy.store99.bookstore.like.repository.LikeRepositoryCustom;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class LikeRepositoryImpl extends QuerydslRepositorySupport implements LikeRepositoryCustom {

    public LikeRepositoryImpl() {
        super(Like.class);
    }

    @Override
    public List<BookInfoForLikeResponse> getAllByUser(Long userId) {
        QLike like = QLike.like;
        QBook book = QBook.book;

        return from(like)
                .innerJoin(book).on(like.book.eq(book))
                .where(like.user.id.eq(userId))
                .select(new QBookInfoForLikeResponse(
                        book.id,
                        book.bookThumbnailUrl,
                        book.bookTitle,
                        book.bookPrice,
                        book.bookSalePrice
//                        book.authorDtoList
                ))
                .fetch();
    }
}

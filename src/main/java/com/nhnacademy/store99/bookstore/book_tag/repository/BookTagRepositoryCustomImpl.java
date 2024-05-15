package com.nhnacademy.store99.bookstore.book_tag.repository;

import com.nhnacademy.store99.bookstore.book.dto.response.BookResponse;
import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book_tag.entity.BookTag;
import com.nhnacademy.store99.bookstore.book_tag.entity.QBookTag;
import com.nhnacademy.store99.bookstore.tag.entity.QTag;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BookTagRepositoryCustomImpl extends QuerydslRepositorySupport implements BookTagRepositoryCustom {

    public BookTagRepositoryCustomImpl() {
        super(BookTag.class);
    }


    @Override
    public List<BookResponse.TagDTO> findByBookId(Long bookId) {
        QTag tag = QTag.tag;
        QBookTag bookTag = QBookTag.bookTag;
        QBook book = QBook.book;

        return from(bookTag)
                .leftJoin(bookTag.book, book)
                .leftJoin(bookTag.tag, tag)
                .where(bookTag.book.id.eq(bookId))
                .where(bookTag.tag.id.eq(tag.id))
                .select(
                        Projections.constructor(BookResponse.TagDTO.class,
                                tag.tagName
                        )
                ).fetch();
    }
}

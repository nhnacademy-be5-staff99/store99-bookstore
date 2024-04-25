package com.nhnacademy.store99.bookstore.book_image.repository.impl;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book_image.entity.BookImage;
import com.nhnacademy.store99.bookstore.book_image.entity.QBookImage;
import com.nhnacademy.store99.bookstore.book_image.repository.BookImageRepository;
import com.nhnacademy.store99.bookstore.book_image.response.BookImageDTO;
import com.nhnacademy.store99.bookstore.file.entity.File;
import com.nhnacademy.store99.bookstore.file.entity.QFile;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Component;
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

@Component
public class BookImageRepositoryImpl extends QuerydslRepositorySupport implements BookImageRepository {
    public BookImageRepositoryImpl(Class<?> domainClass) {
        super(BookImage.class);
    }

    @Override
    public BookImageDTO getBookImageData(Long bookId) {
        QBook book = QBook.book;
        QBookImage bookImage = QBookImage.bookImage;
        QFile file = QFile.file;

        Book bookJPQLQuery = from(book).where(book.id.eq(bookId)).select(book).fetchOne();

        File fileJPQLQuery = from(file).join(bookImage.files).where(bookImage.files.id.eq(file.id))
                .where(bookImage.book.id.eq(bookJPQLQuery.getId()))
                .select(file).fetchOne();

        return new BookImageDTO(fileJPQLQuery.getId(), fileJPQLQuery.getFileUrl(), fileJPQLQuery.getFileName());
    }
}

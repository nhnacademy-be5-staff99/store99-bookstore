package com.nhnacademy.store99.bookstore.book_image.repository.impl;

import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book_image.entity.BookImage;
import com.nhnacademy.store99.bookstore.book_image.entity.QBookImage;
import com.nhnacademy.store99.bookstore.book_image.repository.BookImageRepository;
import com.nhnacademy.store99.bookstore.book_image.response.BookImageDTO;
import com.nhnacademy.store99.bookstore.file.entity.QFile;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

@Component
public class BookImageRepositoryImpl extends QuerydslRepositorySupport implements BookImageRepository {
    public BookImageRepositoryImpl() {
        super(BookImage.class);
    }


    /*

        select * from book_images as bi join books as b join files as f
         where bi.book_id = b.book_id and bi.file_id = f.file_id;
     */
    @Override
    public BookImageDTO getBookImageData(Long bookId) {
        QBook book = QBook.book;
        QBookImage bookImage = QBookImage.bookImage;
        QFile file = QFile.file;
        return from(bookImage).join(bookImage.files, file).join(bookImage.book, book)
                .where(bookImage.book.id.eq(bookId)).select(Projections.constructor(BookImageDTO.class,
                        bookImage.files.id,
                        bookImage.files.fileUrl,
                        bookImage.files.fileName
                )).fetchFirst();
    }
}
package com.nhnacademy.store99.bookstore.book_author.repository.imp;

import com.nhnacademy.store99.bookstore.author.entity.Author;
import com.nhnacademy.store99.bookstore.author.entity.QAuthor;
import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.entity.QBookAuthor;
import com.nhnacademy.store99.bookstore.book_author.repository.BookAuthorRepositoryCustom;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorAPIResponse;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorName;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BookAuthorRepositoryImp extends QuerydslRepositorySupport implements BookAuthorRepositoryCustom {
    public BookAuthorRepositoryImp() {
        super(BookAuthor.class);
    }

    @Override
    public List<BookAuthorAPIResponse> findBookAuthorsByIdGreaterThan(Long id) {
        QBook book = QBook.book;
        QAuthor author = QAuthor.author;
        QBookAuthor bookAuthor = QBookAuthor.bookAuthor;

        return from(bookAuthor)
                .join(bookAuthor.book, book)
                .join(bookAuthor.author, author)
                .select(Projections.bean(BookAuthorAPIResponse.class,
                        bookAuthor.book, bookAuthor.author.authorName)).distinct().fetch();
//        return from(bookAuthor)
//                .select(new QBookAuthorAPIResponse());
    }

    @Override
    public List<BookAuthorName> findBookAuthorByBookId(Long id) {
        QBook book = QBook.book;
        QAuthor author = QAuthor.author;
        QBookAuthor bookAuthor = QBookAuthor.bookAuthor;
        return from(bookAuthor)
                .join(bookAuthor.book, book)
                .join(bookAuthor.author, author)
                .where(bookAuthor.book.id.eq(book.id))
                .where(bookAuthor.author.id.eq(author.id))
                .select(Projections.bean(BookAuthorName.class,
                        book.bookTitle, author.authorName)).distinct()
                .fetch();
//        return from(bookAuthor)
//                .select(bookAuthor.id, book.id)
//                .fetchOne();
    }

    @Override
    public List<BookAuthor> getBookAuthorsByIdGreaterThan(Long bookId) {
        QBook book = QBook.book;
        QAuthor author = QAuthor.author;
        QBookAuthor bookAuthor = QBookAuthor.bookAuthor;

//        return from(book, bookAuthor, author)
//                .innerJoin(bookAuthor.book, book)
//                .innerJoin(bookAuthor.author, author)
//                .where(bookAuthor.book.id.eq(book.id))
//                .where(bookAuthor.author.id.eq(author.id))
//                .select(book, author.authorName)
//                .fetch();

        return from(bookAuthor)
                .join(bookAuthor.book, book)
                .join(bookAuthor.author, author)
                .where(bookAuthor.id.gt(bookId))
                .select(bookAuthor).distinct().fetch();
    }

    @Override
    public List<Author> testQDLS() {
        return null;
    }
}

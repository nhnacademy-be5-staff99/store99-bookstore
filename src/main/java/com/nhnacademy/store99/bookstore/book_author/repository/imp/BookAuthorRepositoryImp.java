package com.nhnacademy.store99.bookstore.book_author.repository.imp;

import com.nhnacademy.store99.bookstore.author.entity.QAuthor;
import com.nhnacademy.store99.bookstore.book.entity.BookWithAuthor;
import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.entity.QBookAuthor;
import com.nhnacademy.store99.bookstore.book_author.repository.BookAuthorRepositoryCustom;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorAPIResponse;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorName;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * <h2>도서-작가 레포지토리 인터페이스 구현체 </h2>
 * 인터페이스의 Named Query 메소드 실행후 결과값을 <br>
 * Query DSL을 사용하여 데이터 반환.
 *
 * @author yrrho2
 */
public class BookAuthorRepositoryImp extends QuerydslRepositorySupport implements BookAuthorRepositoryCustom {
    public BookAuthorRepositoryImp() {
        super(BookAuthor.class);
    }


    @Override
    public List<BookWithAuthor> findBooksByIdGreaterThanEqual(Long id) {
        QBook book = QBook.book;
        QAuthor author = QAuthor.author;
        QBookAuthor bookAuthor = QBookAuthor.bookAuthor;

        return from(bookAuthor)
                .join(bookAuthor.book, book)
                .join(bookAuthor.author, author)
                .where(bookAuthor.book.id.eq(book.id))
                .where(bookAuthor.author.id.eq(author.id))
                .select(Projections.bean(BookWithAuthor.class,
                        bookAuthor.book, bookAuthor.author))
                .fetch();
    }

    /**
     * 파라미터보다 큰 id를 가진 BookAuthor 테이블을 참조하여
     * BookAuthorAPIResponse List를 반환
     *
     * @param id BookAuhtor의 id
     * @return Book객체와 author.name의 List반환.
     */
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
    }


    // BookId를 입력받아 해당되는 작가를 출력
    // 테스트용
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
    }


    // 입력받은 bookId보다 큰 BookAuthor객체 반환
    // EntityGraph를 사용한 Fetch Join 테스트용
    @Override
    public List<BookAuthor> getBookAuthorsByIdGreaterThan(Long bookId) {
        QBook book = QBook.book;
        QAuthor author = QAuthor.author;
        QBookAuthor bookAuthor = QBookAuthor.bookAuthor;

        return from(bookAuthor)
                .join(bookAuthor.book, book)
                .join(bookAuthor.author, author)
                .where(bookAuthor.id.gt(bookId))
                .select(bookAuthor).distinct().fetch();
    }

}

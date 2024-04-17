package com.nhnacademy.store99.bookstore.book_author.repository;


import static org.mockito.ArgumentMatchers.anyLong;

import com.nhnacademy.store99.bookstore.author.entity.Author;
import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.BookRequest;
import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


/**
 * 도서-저자 레포지토리 테스트
 *
 * @author yrrho2
 */
@DataJpaTest
class BookAuthorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @DisplayName("작가id로 도서 조회 테스트")
    @Test
    void findBookAuthorsByAuthorId() {
        // given
        Book book = injectBook(getBookReqeust()).get();
        Book persistedBook = entityManager.persist(book);
        Author author = Author.builder().authorName("TestName").build();
        Author persistedAuthor = entityManager.persist(author);
        BookAuthor bookAuthor = BookAuthor.builder()
                .book(persistedBook).author(persistedAuthor).build();
        BookAuthor persistedBookAuthor = entityManager.persist(bookAuthor);


        // when
        List<BookAuthor> responses = bookAuthorRepository.findBookAuthorsByAuthorId(persistedAuthor.getId());


        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(responses).isNotNull();
        });

    }

    @DisplayName("결과 없는 작가 조회   ")
    @Test
    void voidBookAuthorTest() {
        // when
        List<BookAuthor> responses = bookAuthorRepository.findBookAuthorsByAuthorId(anyLong());

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(responses).hasSize(0);
        });
    }

    private BookRequest getBookReqeust() {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setBookIsbn13("1234567890123");
        bookRequest.setBookIsbn10("1234567890");
        bookRequest.setBookTitle("testbook");
        bookRequest.setBookContents("book test contents");
        bookRequest.setBookDescription("book test Description");
        bookRequest.setBookPublisher("book test publisher");
        bookRequest.setBookDateTime(LocalDateTime.now());
        bookRequest.setBookPrice(200);
        bookRequest.setBookSalePrice(100);
        bookRequest.setBookIsPacked(true);
        bookRequest.setBookThumbnailUrl("asd.asdasd.asd");
        bookRequest.setBookStock(50);
        return bookRequest;
    }

    private Optional<Book> injectBook(BookRequest bookRequest) {
        return Optional.ofNullable(Book.builder()
                .bookIsbn13(bookRequest.getBookIsbn13())
                .bookIsbn10(bookRequest.getBookIsbn10())
                .bookTitle(bookRequest.getBookTitle())
                .bookContents(bookRequest.getBookContents())
                .bookDescription(bookRequest.getBookDescription())
                .bookPublisher(bookRequest.getBookPublisher())
                .bookDate(bookRequest.getBookDateTime())
                .bookPrice(bookRequest.getBookPrice())
                .bookSalePrice(bookRequest.getBookSalePrice())
                .bookIsPacked(bookRequest.getBookIsPacked())
                .bookThumbnailUrl(bookRequest.getBookThumbnailUrl())
                .bookStock(bookRequest.getBookStock())
                .build());
    }
}
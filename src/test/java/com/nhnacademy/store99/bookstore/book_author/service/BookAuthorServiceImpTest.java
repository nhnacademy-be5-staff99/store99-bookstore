package com.nhnacademy.store99.bookstore.book_author.service;


import com.nhnacademy.store99.bookstore.author.entity.Author;
import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.repository.BookAuthorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * 도서-저자 서비스 구현체 테스트
 *
 * @author yrrho2
 */
@ExtendWith(MockitoExtension.class)
class BookAuthorServiceImpTest {

    @InjectMocks
    private BookAuthorServiceImp bookAuthorService;
    @Mock
    private BookAuthorRepository bookAuthorRepository;


    @DisplayName("작가Id로 도서 조회")
    @Test
    void testGetAuthorByAuthorId() {
        // given
        Long authorId = Mockito.anyLong();
        BookAuthor bookAuthor = BookAuthor.builder().build();
        List<BookAuthor> testList = new ArrayList<>();
        testList.add(bookAuthor);
        BDDMockito.given(bookAuthorRepository.findBookAuthorsByAuthorId(authorId)).willReturn(testList);

        // when
        List<BookAuthor> baList = bookAuthorService.getAuthorByAuthorId(authorId);

        // then
        Assertions.assertThat(baList).hasSizeGreaterThan(0);
        Assertions.assertThat(baList).isNotNull();

    }

    @DisplayName("도서id로 작가 조회")
    @Test
    void testGetAuthorBook() {
        // given
        Long bookId = 1L;
        Author author = Author.builder().authorName("TestName").build();
        BookAuthor bookAuthor = BookAuthor.builder().author(author).build();
        BDDMockito.given(bookAuthorRepository.findById(bookId)).willReturn(Optional.ofNullable(bookAuthor));

        // when
        BookAuthor bookAuthorExp = bookAuthorRepository.findById(bookId).get();

        // then
        Assertions.assertThat(bookAuthorExp).isNotNull();
        Assertions.assertThat(bookAuthorExp.getAuthor().getAuthorName()).isEqualTo(author.getAuthorName());
    }
}
package com.nhnacademy.store99.bookstore.book_author.controller;

import com.nhnacademy.store99.bookstore.book_author.service.BookAuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(BookAuthorController.class)
class BookAuthorControllerTest {

    private BookAuthorService bookAuthorService;

    @Test
    void TestGetBookAuthorbyAuthorId() {
        // given
        Long authorId = Mockito.anyLong();
        BDDMockito.given(bookAuthorService.getAuthorByAuthorId(authorId)).willReturn(Mockito.anyList());


    }

    @Test
    void TestGetBookAuthor() {

    }
}
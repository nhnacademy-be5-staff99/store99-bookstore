package com.nhnacademy.store99.bookstore.book.controller;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.store99.bookstore.book.TestSupport;
import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.BookRequest;
import com.nhnacademy.store99.bookstore.book.service.BookServiceImp;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
class BookControllerTest extends TestSupport {
    @MockBean
    BookServiceImp bookServiceImp;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetBook() throws Exception {
        // given
        BookRequest bookRequest = getBookReqeust();
        given(bookServiceImp.getBook(anyLong())).willReturn(injectBook(bookRequest));

        // when
        mockMvc.perform(get("/v1/book/0"))
                .andExpect(status().isOk());

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
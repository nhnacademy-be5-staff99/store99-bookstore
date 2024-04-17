package com.nhnacademy.store99.bookstore.book_author.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorResponse;
import com.nhnacademy.store99.bookstore.book_author.service.BookAuthorService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author yrrho2
 */
@WebMvcTest(value = BookAuthorController.class)
class BookAuthorControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected AdminCheckService adminCheckService;

    @MockBean
    protected BookAuthorService bookAuthorService;


    @DisplayName("저자의 도서 조회")
    @Test
    void TestGetBookAuthorbyAuthorId() throws Exception {
        // given
        Long authorId = Mockito.anyLong();
        BDDMockito.given(bookAuthorService.getAuthorByAuthorId(authorId)).willReturn(Mockito.anyList());
        List<BookAuthorResponse> baList = new ArrayList<>();
        int anyInt = new Random().nextInt();

        // when
        String response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/open/v1/books/author/{authorId}", anyInt))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then

        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK).resultMessage("OK").build();
        CommonResponse<List<BookAuthorResponse>> commonResponse =
                CommonResponse.<List<BookAuthorResponse>>builder().header(commonHeader)
                        .result(baList)
                        .build();
        String expected = objectMapper.writeValueAsString(commonResponse);
        Assertions.assertThat(response).isEqualTo(expected);
    }

    /**
     * 책 한권의 id에서 작가 확인
     */
    @DisplayName("도서의 저자 조회")
    @Test
    void TestGetBookAuthor() throws Exception {
        // given
        Long bookId = Mockito.anyLong();
        BookAuthorResponse anyResponse = BookAuthorResponse.builder().build();
        BDDMockito.given(bookAuthorService.getAuthorBook(bookId)).willReturn(anyResponse);

        // when
        String response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/open/v1/books/author/book")
                                .param("bookId", String.valueOf(bookId)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        //then
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK).resultMessage("OK").build();
        CommonResponse<BookAuthorResponse> commonResponse = CommonResponse
                .<BookAuthorResponse>builder()
                .header(commonHeader)
                .result(anyResponse).build();

        String expected = objectMapper.writeValueAsString(commonResponse);
        Assertions.assertThat(response).isEqualTo(expected);
    }
}
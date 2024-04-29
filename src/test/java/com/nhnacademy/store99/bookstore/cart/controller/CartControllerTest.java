package com.nhnacademy.store99.bookstore.cart.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.store99.bookstore.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.bookstore.cart.dto.response.CartItemResponse;
import com.nhnacademy.store99.bookstore.cart.exception.CartBadRequestException;
import com.nhnacademy.store99.bookstore.cart.service.CartQueryService;
import com.nhnacademy.store99.bookstore.cart.service.CartService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.config.RestDocSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@WebMvcTest(CartController.class)
class CartControllerTest extends RestDocSupport {
    @MockBean
    private CartService cartService;

    @MockBean
    private CartQueryService cartQueryService;

    @Test
    @DisplayName("장바구니에 책 추가 성공")
    void addBookToCartSuccessfully() throws Exception {
        // given
        CartItemRequest request = new CartItemRequest(1L, 10);

        // when
        String actualResponse = mockMvc.perform(post("/v1/cart/books")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        // then
        verify(cartService).addBookToCart(any(CartItemRequest.class));

        CommonHeader header = CommonHeader.builder().httpStatus(HttpStatus.CREATED).resultMessage("Success").build();
        CommonResponse<List<CartItemResponse>> response =
                CommonResponse.<List<CartItemResponse>>builder().header(header).build();
        String expectedResponse = objectMapper.writeValueAsString(response);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    @DisplayName("장바구니에 책 추가 실패 - 없는 책 아이디")
    void addBookToCartFailure() throws Exception {
        // given
        CartItemRequest request = new CartItemRequest(1L, 10);
        doThrow(CartBadRequestException.BookNotFound(1L)).when(cartService).addBookToCart(any(CartItemRequest.class));

        // when
        String actualResponse = mockMvc.perform(post("/v1/cart/books")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        // then
        CommonHeader header = CommonHeader.builder().httpStatus(HttpStatus.BAD_REQUEST)
                .resultMessage(CartBadRequestException.BookNotFound(1L).getMessage()).build();
        CommonResponse<Void> response = CommonResponse.<Void>builder().header(header).build();
        String expectedResponse = objectMapper.writeValueAsString(response);
        assertThat(actualResponse).isEqualTo(expectedResponse);

        verify(cartService).addBookToCart(any(CartItemRequest.class));
    }

    @Test
    @DisplayName("사용자의 장바구니 아이템 조회 성공")
    void getCartItemsByUserSuccessfully() throws Exception {
        // given
        List<CartItemResponse> cartItemResponses = new ArrayList<>();
        CartItemResponse cartItemResponse1 = new CartItemResponse(1L, "book title1", 10000,
                9000, "https://via.placeholder.com/200x303", 10, 1);
        cartItemResponses.add(cartItemResponse1);
        CartItemResponse cartItemResponse2 = new CartItemResponse(2L, "book title2", 10000,
                9000, "https://via.placeholder.com/200x303", 10, 1);
        cartItemResponses.add(cartItemResponse2);

        given(cartQueryService.getCartItemsByUser()).willReturn(cartItemResponses);

        // when
        String actualResponse = mockMvc.perform(get("/v1/cart/books")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then
        CommonHeader header = CommonHeader.builder().httpStatus(HttpStatus.OK).resultMessage("Success").build();
        CommonResponse<List<CartItemResponse>> response =
                CommonResponse.<List<CartItemResponse>>builder().header(header).result(cartItemResponses).build();
        String expectedResponse = objectMapper.writeValueAsString(response);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    @DisplayName("Modify book quantity in cart successfully")
    void modifyBookQuantityInCartSuccessfully() throws Exception {
        // given
        CartItemRequest request = new CartItemRequest(1L, 5);

        // when
        mockMvc.perform(put("/v1/cart/books")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        verify(cartService).modifyBookQuantityInCart(any(CartItemRequest.class));
    }

    @Test
    @DisplayName("Modify book quantity in cart failure - nonexistent book ID")
    void modifyBookQuantityInCartFailure() throws Exception {
        // given
        CartItemRequest request = new CartItemRequest(1L, 5);
        doThrow(CartBadRequestException.BookNotFound(1L)).when(cartService)
                .modifyBookQuantityInCart(any(CartItemRequest.class));

        // when
        mockMvc.perform(put("/v1/cart/books")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        // then
        verify(cartService).modifyBookQuantityInCart(any(CartItemRequest.class));
    }

//    @Test
//    @DisplayName("Modify book quantity in cart failure - invalid quantity")
//    void modifyBookQuantityInCartFailureInvalidQuantity() throws Exception {
//        // given
//        CartItemRequest request = new CartItemRequest(1L, -5);
//        doThrow(CartBadRequestException.InvalidQuantity()).when(cartService)
//                .modifyBookQuantityInCart(any(CartItemRequest.class));
//
//        // when
//        mockMvc.perform(put("/v1/cart/books")
//                        .header("X-USER-ID", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isBadRequest());
//
//        // then
//        verify(cartService).modifyBookQuantityInCart(any(CartItemRequest.class));
//    }
}
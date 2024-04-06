package com.nhnacademy.store99.bookstore.auth.controller;

import com.nhnacademy.store99.bookstore.auth.dto.response.AdminCheckResponse;
import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.config.RestDocSupport;
import com.nhnacademy.store99.bookstore.user.excepiton.UserNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


class AdminCheckControllerTest extends RestDocSupport {
    @MockBean
    private AdminCheckService adminCheckService;

    @Test
    void checkAdmin_true() throws Exception {
        // given
        Long userId = Mockito.anyLong();
        BDDMockito.given(adminCheckService.isAdmin(userId)).willReturn(true);

        // when
        String response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/v1/admin/check")
                                .header("X-USER-ID", userId))
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk()
                )
                .andReturn().getResponse().getContentAsString();

        // then
        CommonHeader commonHeader = CommonHeader.builder().httpStatus(HttpStatus.OK).resultMessage("Success").build();
        CommonResponse<AdminCheckResponse> commonResponse =
                CommonResponse.<AdminCheckResponse>builder().header(commonHeader).result(new AdminCheckResponse(true))
                        .build();
        String expected = objectMapper.writeValueAsString(commonResponse);
        Assertions.assertThat(response).isEqualTo(expected);
    }

    @Test
    void checkAdmin_false() throws Exception {
        // given
        Long userId = Mockito.anyLong();
        BDDMockito.given(adminCheckService.isAdmin(userId)).willReturn(false);

        // when
        String response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/v1/admin/check")
                                .header("X-USER-ID", userId))
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk()
                )
                .andReturn().getResponse().getContentAsString();

        // then
        CommonHeader commonHeader = CommonHeader.builder().httpStatus(HttpStatus.OK).resultMessage("Success").build();
        CommonResponse<AdminCheckResponse> commonResponse =
                CommonResponse.<AdminCheckResponse>builder().header(commonHeader).result(new AdminCheckResponse(false))
                        .build();
        String expected = objectMapper.writeValueAsString(commonResponse);
        Assertions.assertThat(response).isEqualTo(expected);
    }

    @Test
    void checkAdmin_userNotFound() throws Exception {
        // given
        Long userId = Mockito.anyLong();
        BDDMockito.given(adminCheckService.isAdmin(userId)).willThrow(new UserNotFoundException(userId));

        // when
        String response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/v1/admin/check")
                                .header("X-USER-ID", userId))
                .andExpectAll(
                        MockMvcResultMatchers.status().isNotFound()
                )
                .andReturn().getResponse().getContentAsString();

        // then
        CommonHeader commonHeader =
                CommonHeader.builder().httpStatus(HttpStatus.NOT_FOUND)
                        .resultMessage(String.format("User not found (userId: %d)", userId)).build();
        CommonResponse<String>
                commonResponse = CommonResponse.<String>builder().header(commonHeader).build();
        String expected = objectMapper.writeValueAsString(commonResponse);
        Assertions.assertThat(response).isEqualTo(expected);
    }
}
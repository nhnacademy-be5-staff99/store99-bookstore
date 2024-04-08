package com.nhnacademy.store99.bookstore.auth.controller;

import com.nhnacademy.store99.bookstore.auth.dto.response.AdminCheckResponse;
import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.config.RestDocSupport;
import com.nhnacademy.store99.bookstore.user.excepiton.UserNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 관리자 여부 확인 컨트롤러 테스트
 *
 * @author seunggyu-kim
 */
@WebMvcTest(AdminCheckController.class)
class AdminCheckControllerTest extends RestDocSupport {
    @MockBean
    private AdminCheckService adminCheckService;

    /**
     * 관리자 여부 확인 테스트
     * <p>사용자가 관리자 권한을 갖고있는 경우
     *
     * @throws Exception
     */
    @DisplayName("관리자 여부 확인 - 관리자인 경우")
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

    /**
     * 관리자 여부 확인 테스트
     * <p>사용자가 관리자 권한을 갖고있지 않은 경우
     *
     * @throws Exception
     */
    @DisplayName("관리자 여부 확인 - 관리자가 아닌 경우")
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

    /**
     * 관리자 여부 확인 테스트
     * <p>사용자가 존재하지 않는 경우
     *
     * @throws Exception
     */
    @DisplayName("관리자 여부 확인 - 사용자가 존재하지 않는 경우")
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
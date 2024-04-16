package com.nhnacademy.store99.bookstore.user.controller;

import static org.mockito.ArgumentMatchers.any;

import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.config.RestDocSupport;
import com.nhnacademy.store99.bookstore.user.dto.AuthorizationRequest;
import com.nhnacademy.store99.bookstore.user.dto.AuthorizationResponse;
import com.nhnacademy.store99.bookstore.user.exception.UserNotFoundByEmailException;
import com.nhnacademy.store99.bookstore.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Ahyeon Song
 */
@WebMvcTest(UserController.class)
class UserControllerTest extends RestDocSupport {
    @MockBean
    private UserService userService;

    @DisplayName("POST /login 요청 성공")
    @Test
    void loginSuccessTest() throws Exception {
        //given
        AuthorizationRequest testRequest = new AuthorizationRequest("test@gmail.com");
        AuthorizationResponse testResponse = new AuthorizationResponse();
        testResponse.setUserId(1L);
        testResponse.setEmail("test@gmail.com");
        testResponse.setPassword("test");
        testResponse.setAuth("USER");

        String content = objectMapper.writeValueAsString(testRequest);
        BDDMockito.given(userService.userLogin(any(AuthorizationRequest.class))).willReturn(testResponse);

        //when
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/open/v1/user/login")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        //then
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .resultMessage("Success")
                .build();
        CommonResponse<AuthorizationResponse> commonResponse = CommonResponse.<AuthorizationResponse>builder()
                .header(commonHeader)
                .result(testResponse)
                .build();
        String expect = objectMapper.writeValueAsString(commonResponse);
        Assertions.assertThat(response).isEqualTo(expect);
    }

    @DisplayName("POST /login 요청 실패 - 요청이 email 형식이 아님")
    @Test
    void loginFailTest() throws Exception {
        //given
        AuthorizationRequest testRequest = new AuthorizationRequest("test");
        String content = objectMapper.writeValueAsString(testRequest);

        //when
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/open/v1/user/login")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        //then
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .resultMessage("형식에 맞지 않은 요청입니다.")
                .build();
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .header(commonHeader)
                .build();

        String expect = objectMapper.writeValueAsString(commonResponse);
        Assertions.assertThat(response).isEqualTo(expect);

    }

    @DisplayName("POST /login 요청 실패 - 사용자를 찾을 수 없음")
    @Test
    void loginFailTest2() throws Exception {
        //given
        AuthorizationRequest testRequest = new AuthorizationRequest("test@gmail.com");
        String content = objectMapper.writeValueAsString(testRequest);

        BDDMockito.given(userService.userLogin(any(AuthorizationRequest.class)))
                .willThrow(new UserNotFoundByEmailException(testRequest.getEmail()));

        //when
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/open/v1/user/login")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString();

        //then
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .resultMessage(String.format("User not found (UserEmail: %s)", testRequest.getEmail()))
                .build();
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .header(commonHeader)
                .build();

        String expect = objectMapper.writeValueAsString(commonResponse);
        Assertions.assertThat(response).isEqualTo(expect);

    }
}
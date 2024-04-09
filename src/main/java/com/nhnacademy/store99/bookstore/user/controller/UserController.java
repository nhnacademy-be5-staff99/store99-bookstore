package com.nhnacademy.store99.bookstore.user.controller;

import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.user.dto.AuthorizationRequest;
import com.nhnacademy.store99.bookstore.user.dto.AuthorizationResponse;
import com.nhnacademy.store99.bookstore.user.service.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<AuthorizationResponse>> userLogin(@Valid @RequestBody AuthorizationRequest request) {
        AuthorizationResponse response = userService.userLogin(request);
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .resultMessage("Success")
                .build();
        CommonResponse<AuthorizationResponse> commonResponse = CommonResponse.<AuthorizationResponse>builder()
                .header(commonHeader)
                .result(response)
                .build();
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(commonResponse);
    }

}

package com.nhnacademy.store99.bookstore.auth.controller;

import com.nhnacademy.store99.bookstore.auth.dto.response.AdminCheckResponse;
import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * 관리자 권한 체크 Controller
 *
 * @author seunggyu-kim
 */
@RequiredArgsConstructor
@RestController
public class AdminCheckController {
    private final AdminCheckService adminCheckService;

    /**
     * 관리자 권한 체크 API
     * <p>/v1/admin/check
     *
     * @param xUserId Header 사용자 번호
     * @return 관리자 여부
     */
    @GetMapping("/v1/admin/check")
    public ResponseEntity<CommonResponse<AdminCheckResponse>> checkAdmin(@RequestHeader("X-USER-ID") Long xUserId) {
        AdminCheckResponse response = new AdminCheckResponse(adminCheckService.isAdmin(xUserId));
        CommonHeader commonHeader = CommonHeader.builder().httpStatus(HttpStatus.OK).resultMessage("Success").build();
        CommonResponse<AdminCheckResponse> commonResponse =
                CommonResponse.<AdminCheckResponse>builder().header(commonHeader).result(response).build();
        return ResponseEntity.ok(commonResponse);
    }
}

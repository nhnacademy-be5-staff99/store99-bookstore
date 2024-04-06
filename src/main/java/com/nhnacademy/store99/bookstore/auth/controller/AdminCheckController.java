package com.nhnacademy.store99.bookstore.auth.controller;

import com.nhnacademy.store99.bookstore.auth.dto.response.AdminCheckResponse;
import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminCheckController {
    private final AdminCheckService adminCheckService;

    @GetMapping("/v1/admin/check")
    public ResponseEntity<AdminCheckResponse> checkAdmin(@RequestHeader("X-USER-ID") Long xUserId) {
        AdminCheckResponse response = new AdminCheckResponse(adminCheckService.isAdmin(xUserId));
        return ResponseEntity.ok(response);
    }
}

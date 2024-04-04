package com.nhnacademy.store99.bookstore.auth.controller;

import com.nhnacademy.store99.bookstore.auth.dto.response.AdminCheckResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCheckController {
    @GetMapping("/v1/admin/check")
    public ResponseEntity<AdminCheckResponse> checkAdmin() {
        AdminCheckResponse response = new AdminCheckResponse(true);
        return ResponseEntity.ok(response);
    }
}

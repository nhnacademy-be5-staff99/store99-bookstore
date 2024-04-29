package com.nhnacademy.store99.bookstore.user.controller;

import com.nhnacademy.store99.bookstore.user.dto.MainMyPageResponse;
import com.nhnacademy.store99.bookstore.user.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 메인 마이페이지 화면
 *
 * @author Ahyeon Song
 */
@RestController
@RequestMapping("/v1/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping
    public MainMyPageResponse getMainMyPage(@RequestHeader("X-USER-ID") Long xUserId) {
        return myPageService.getMainMyPage(xUserId);
    }
}

package com.nhnacademy.store99.bookstore.point_history.controller;

import com.nhnacademy.store99.bookstore.point_history.dto.UserPointResponse;
import com.nhnacademy.store99.bookstore.point_history.service.PointService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/points")
public class PointController {

    private final PointService pointService;

    /**
     * userId 에 해당하는 포인트 내역 리스트 조회
     *
     * @param xUserId
     * @return UserPointResponse list
     */
    @GetMapping
    public List<UserPointResponse> getUserPointHistories(@RequestHeader("X-USER-ID") Long xUserId) {
        return pointService.getUserPointHistories(xUserId);
    }


}

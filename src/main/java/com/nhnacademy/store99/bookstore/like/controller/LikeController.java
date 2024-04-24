package com.nhnacademy.store99.bookstore.like.controller;

import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.like.dto.request.LikeRequest;
import com.nhnacademy.store99.bookstore.like.service.LikeService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * LikeController
 *
 * @author 이서연
 */
@RestController
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/v1/likes")
    public ResponseEntity<CommonResponse<Void>> postAddLike(@RequestBody @Valid LikeRequest req) {
        likeService.addLike(req);
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.CREATED)
                .resultMessage("Successfully add like")
                .build();
        CommonResponse<Void> commonResponse = CommonResponse.<Void>builder()
                .header(commonHeader)
                .build();
        return ResponseEntity.ok(commonResponse);

    }


}

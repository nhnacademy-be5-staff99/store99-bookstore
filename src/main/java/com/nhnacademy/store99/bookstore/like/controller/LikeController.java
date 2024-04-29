package com.nhnacademy.store99.bookstore.like.controller;

import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.like.dto.request.LikeRequest;
import com.nhnacademy.store99.bookstore.like.service.LikeService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * LikeController
 *
 * @author 이서연
 */
@RestController
@RequestMapping("/v1/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
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

    @DeleteMapping("/{likeId}")
    public ResponseEntity<CommonResponse<String>> postDeleteLike(@RequestBody @Valid @PathVariable Long likeId) {
        String response = likeService.deleteLike(likeId);
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .resultMessage("Successfully delete like")
                .build();
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .header(commonHeader)
                .result(response)
                .build();
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("/likeCnt")
    public ResponseEntity<CommonResponse<Long>> getLikeBook(@RequestParam(value = "bookId") @Valid Long bookId) {
        Long cnt = likeService.countByBookId(bookId);
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .build();
        CommonResponse<Long> commonResponse = CommonResponse.<Long>builder()
                .header(commonHeader)
                .result(cnt)
                .build();
        return ResponseEntity.ok(commonResponse);
    }


}

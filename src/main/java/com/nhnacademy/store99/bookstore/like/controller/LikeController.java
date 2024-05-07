package com.nhnacademy.store99.bookstore.like.controller;

import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.like.dto.request.LikeRequest;
import com.nhnacademy.store99.bookstore.like.dto.response.BookInfoForLikeResponse;
import com.nhnacademy.store99.bookstore.like.service.LikeQueryService;
import com.nhnacademy.store99.bookstore.like.service.LikeService;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
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
@RequestMapping("/v1")
public class LikeController {

    private final LikeService likeService;
    private final LikeQueryService likeQueryService;

    public LikeController(LikeService likeService, LikeQueryService likeQueryService) {
        this.likeService = likeService;
        this.likeQueryService = likeQueryService;
    }

    @PostMapping("/likes")
    public ResponseEntity<CommonResponse<Void>> postAddLike(@RequestBody @Valid LikeRequest req) {
        likeService.addLike(req);
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.CREATED)
                .resultMessage("Successfully added like")
                .build();
        CommonResponse<Void> commonResponse = CommonResponse.<Void>builder()
                .header(commonHeader)
                .build();
        return ResponseEntity.ok(commonResponse);

    }

    @DeleteMapping("/likes/{likeId}")
    public ResponseEntity<CommonResponse<String>> postDeleteLike(@RequestBody @Valid @PathVariable Long likeId) {
        String response = likeService.deleteLike(likeId);
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .resultMessage("Successfully deleted like")
                .build();
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .header(commonHeader)
                .result(response)
                .build();
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping(value = "/likes/count", params = "bookId")
    public ResponseEntity<CommonResponse<Long>> getLikeBook(@RequestParam(value = "bookId") @Valid Long bookId) {
        Long cnt = likeService.countLikesByBookId(bookId);
        CommonHeader commonHeader = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .build();
        CommonResponse<Long> commonResponse = CommonResponse.<Long>builder()
                .header(commonHeader)
                .result(cnt)
                .build();
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping(value = "/mylikes", params = {"userId"})
    public Page<BookInfoForLikeResponse> getAllByUser() {
        return likeQueryService.getAllByUser();
    }

}

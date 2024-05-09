package com.nhnacademy.store99.bookstore.like.controller;

import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.like.dto.request.LikeRequest;
import com.nhnacademy.store99.bookstore.like.dto.response.BookInfoForLikeResponse;
import com.nhnacademy.store99.bookstore.like.service.LikeMypageService;
import com.nhnacademy.store99.bookstore.like.service.LikeService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private final LikeMypageService likeMypageService;

    public LikeController(LikeService likeService, LikeMypageService likeMypageService) {
        this.likeService = likeService;
        this.likeMypageService = likeMypageService;
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

    @GetMapping("likes/count/{bookId}")
    public Long getAllByBook(@PathVariable Long bookId) {
        return likeService.countAllByBook(bookId);
    }

    @GetMapping(value = "/mylikes")
    public List<BookInfoForLikeResponse> getAllByUser() {
        return likeMypageService.getAllByUser();
    }

}

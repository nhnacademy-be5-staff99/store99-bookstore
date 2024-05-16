package com.nhnacademy.store99.bookstore.review.controller;

import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.review.dto.request.TextReviewRegisterRequest;
import com.nhnacademy.store99.bookstore.review.exception.RegisterReviewProcessingException;
import com.nhnacademy.store99.bookstore.review.service.ReviewService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/text/register")
    public ResponseEntity<CommonResponse<String>> registerTextReview(
            @RequestBody @Valid TextReviewRegisterRequest request, @RequestHeader("X-USER-ID") Long userId) {
        try {
            reviewService.registerTextReview(request, userId);
            CommonHeader header = CommonHeader.builder()
                    .httpStatus(HttpStatus.OK)
                    .resultMessage("Success")
                    .build();
            CommonResponse<String> response = CommonResponse.<String>builder()
                    .header(header)
                    .result("Successfully registered text review")
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new RegisterReviewProcessingException(ex.getMessage());
        }
    }

}

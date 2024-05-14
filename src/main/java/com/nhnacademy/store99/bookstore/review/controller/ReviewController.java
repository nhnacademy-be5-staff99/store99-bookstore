package com.nhnacademy.store99.bookstore.review.controller;

import com.nhnacademy.store99.bookstore.review.dto.request.TextReviewRegisterRequest;
import com.nhnacademy.store99.bookstore.review.exception.RegisterReviewProcessingException;
import com.nhnacademy.store99.bookstore.review.service.ReviewService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public void registerTextReview(@RequestBody @Valid TextReviewRegisterRequest request) {
        try {
            reviewService.registerTextReview(request);
        } catch (Exception ex) {
            throw new RegisterReviewProcessingException("Not register Review: " + ex);
        }
    }

}

package com.nhnacademy.store99.bookstore.review.service;

import com.nhnacademy.store99.bookstore.review.dto.request.TextReviewRegisterRequest;

public interface ReviewService {

    void registerTextReview(TextReviewRegisterRequest request, Long userId);
}

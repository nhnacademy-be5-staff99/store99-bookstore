package com.nhnacademy.store99.bookstore.review.service;

import com.nhnacademy.store99.bookstore.review.dto.request.TextReviewRegisterRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface ReviewService {

    void registerTextReview(@RequestBody @Valid TextReviewRegisterRequest request);
}

package com.nhnacademy.store99.bookstore.review.service;

import com.nhnacademy.store99.bookstore.review.dto.request.ReviewRegisterRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface ReviewService {

    void registerReview(@RequestBody @Valid ReviewRegisterRequest request);
}

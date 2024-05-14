package com.nhnacademy.store99.bookstore.review.service.impl;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.repository.BookJPARepository;
import com.nhnacademy.store99.bookstore.common.thread_local.XUserIdThreadLocal;
import com.nhnacademy.store99.bookstore.review.dto.request.ReviewRegisterRequest;
import com.nhnacademy.store99.bookstore.review.entity.Review;
import com.nhnacademy.store99.bookstore.review.repository.ReviewRepository;
import com.nhnacademy.store99.bookstore.review.service.ReviewService;
import com.nhnacademy.store99.bookstore.user.entity.User;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookJPARepository bookRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             BookJPARepository bookRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void registerReview(ReviewRegisterRequest request) {

        Long userId = XUserIdThreadLocal.getXUserId();
        if (reviewRepository.isRegister(request.getBookId(), userId)) {
            Book book = bookRepository.findById(request.getBookId()).orElseThrow(null);
            User user = userRepository.findById(userId).orElseThrow(null);
            Review newReview = Review.builder()
                    .reviewDescription(request.getReviewDescription())
                    .reviewRate(request.getReviewRate())
                    .book(book)
                    .user(user)
                    .build();
            reviewRepository.save(newReview);
        }
    }
}

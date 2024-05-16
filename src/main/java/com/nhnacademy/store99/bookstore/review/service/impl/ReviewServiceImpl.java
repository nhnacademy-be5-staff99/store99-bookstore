package com.nhnacademy.store99.bookstore.review.service.impl;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.repository.BookRepository;
import com.nhnacademy.store99.bookstore.review.dto.request.TextReviewRegisterRequest;
import com.nhnacademy.store99.bookstore.review.entity.Review;
import com.nhnacademy.store99.bookstore.review.repository.ReviewRepository;
import com.nhnacademy.store99.bookstore.review.service.ReviewService;
import com.nhnacademy.store99.bookstore.user.entity.User;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             BookRepository bookRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void registerTextReview(TextReviewRegisterRequest request, Long userId) {

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

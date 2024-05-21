package com.nhnacademy.store99.bookstore.review.repository;

import com.nhnacademy.store99.bookstore.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

}

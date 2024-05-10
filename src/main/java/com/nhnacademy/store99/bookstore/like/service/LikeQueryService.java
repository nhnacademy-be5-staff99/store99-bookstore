package com.nhnacademy.store99.bookstore.like.service;

import com.nhnacademy.store99.bookstore.like.dto.response.BookInfoForLikeResponse;
import org.springframework.data.domain.Page;

public interface LikeQueryService {
    Page<BookInfoForLikeResponse> getAllByUser();
}

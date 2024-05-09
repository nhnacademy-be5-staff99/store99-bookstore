package com.nhnacademy.store99.bookstore.like.service;

import com.nhnacademy.store99.bookstore.like.dto.response.BookInfoForLikeResponse;
import java.util.List;

public interface LikeMypageService {

    List<BookInfoForLikeResponse> getAllByUser();
}

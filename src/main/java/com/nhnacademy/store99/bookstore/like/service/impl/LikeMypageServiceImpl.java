package com.nhnacademy.store99.bookstore.like.service.impl;

import com.nhnacademy.store99.bookstore.common.thread_local.XUserIdThreadLocal;
import com.nhnacademy.store99.bookstore.like.dto.response.BookInfoForLikeResponse;
import com.nhnacademy.store99.bookstore.like.repository.LikeRepository;
import com.nhnacademy.store99.bookstore.like.service.LikeMypageService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LikeMypageServiceImpl implements LikeMypageService {

    private final LikeRepository likeRepository;

    public LikeMypageServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public List<BookInfoForLikeResponse> getAllByUser() {
        Long userId = XUserIdThreadLocal.getXUserId();
        List<BookInfoForLikeResponse> likeList = likeRepository.getAllByUser(userId);
        return likeList;
    }
}

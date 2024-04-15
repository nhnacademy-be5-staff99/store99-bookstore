package com.nhnacademy.store99.bookstore.like.service.impl;

import com.nhnacademy.store99.bookstore.like.dto.request.LikeRequest;
import com.nhnacademy.store99.bookstore.like.entity.Like;
import com.nhnacademy.store99.bookstore.like.repository.LikeRepository;
import com.nhnacademy.store99.bookstore.like.service.LikeService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    @Transactional
    public void addLike(LikeRequest likeRequest) {

        if (isLiked(likeRequest.getBook().getId(), likeRequest.getUser().getId())) {
            throw new IllegalStateException("Already pressed the like!!");
        }
        Like newLike = Like.builder()
                .createdAt(LocalDateTime.now())
                .book(likeRequest.getBook())
                .user(likeRequest.getUser())
                .build();

        likeRepository.save(newLike);

    }

    @Override
    @Transactional
    public void deleteLike(Long likeId) {
        Like like = likeRepository.findById(likeId).orElse(null);
        assert like != null;
        if (!isLiked(like.getBook().getId(), like.getUser().getId())) {
            throw new IllegalStateException("Noting deleted the like!");
        }
        likeRepository.deleteById(likeId);
    }

    @Override
    public boolean isLiked(Long bookId, Long userId) {
        return likeRepository.existByBookIdAndUserId(bookId, userId);
    }


    @Override
    public Long countLikesByBookId(Long bookId) {
        return likeRepository.countByBookId(bookId);
    }

//    @Override
//    @Transactional
//    public Page<BookInfoForLikeResponse> getAllByUser(Long userId, Pageable pageable) {
//        likeRepository.findAllByUserId(userId, pageable).getPageable();
//        return null;
//    }

}

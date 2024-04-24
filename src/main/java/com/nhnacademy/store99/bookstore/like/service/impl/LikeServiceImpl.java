package com.nhnacademy.store99.bookstore.like.service.impl;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.repository.BookRepository;
import com.nhnacademy.store99.bookstore.like.dto.request.LikeRequest;
import com.nhnacademy.store99.bookstore.like.entity.Like;
import com.nhnacademy.store99.bookstore.like.repository.LikeRepository;
import com.nhnacademy.store99.bookstore.like.service.LikeService;
import com.nhnacademy.store99.bookstore.user.entity.User;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LikeServiceImpl(LikeRepository likeRepository,
                           UserRepository userRepository,
                           BookRepository bookRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void addLike(LikeRequest likeRequest) {
        if (isLiked(likeRequest.getBookId(), likeRequest.getUserId())) {
            throw new IllegalStateException("Already pressed the like!!");
        }
        Book likeBook = bookRepository.findById(likeRequest.getBookId()).orElseThrow();
        User likeUser = userRepository.findById(likeRequest.getUserId()).orElseThrow();

        Like newLike = Like.builder()
                .createdAt(LocalDateTime.now())
                .book(likeBook)
                .user(likeUser)
                .build();

        likeRepository.save(newLike);
    }

    @Override
    @Transactional
    public String deleteLike(Long likeId) {
        Like like = likeRepository.findById(likeId).orElse(null);
        assert like != null;
        if (!isLiked(like.getBook().getId(), like.getUser().getId())) {
            throw new IllegalStateException("Noting deleted the like!");
        }
        likeRepository.deleteById(likeId);
        return null;
    }

    @Override
    public boolean isLiked(Long bookId, Long userId) {
        return likeRepository.existsByBookIdAndUserId(bookId, userId);
    }

    /**
     * 도서 별 좋아요 수를 조회하는 메소드 입니다.
     *
     * @param bookId
     * @return 권 당 좋아요 갯수
     * @author 이서연
     */
    @Override
    public Long countLikesByBookId(Long bookId) {
        return likeRepository.countLikesByBookId(bookId);
    }

    //    @Override
//    @Transactional
//    public Page<BookInfoForLikeResponse> getAllByUser(Long userId, Pageable pageable) {
//        likeRepository.findAllByUserId(userId, pageable).getPageable();
//        return null;
//    }

}

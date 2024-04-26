package com.nhnacademy.store99.bookstore.like.service;

import com.nhnacademy.store99.bookstore.like.dto.request.LikeRequest;

public interface LikeService {

    /**
     * 좋아요가 생성되는 메소드 입니다.
     *
     * @param likeRequest
     * @author 이서연
     */
    void addLike(LikeRequest likeRequest);

    /**
     * 좋아요가 삭제될 때 작동하는 메소드입니다.
     *
     * @param likeId
     * @return String
     * @author 이서연
     */
    String deleteLike(Long likeId);

    /**
     * 좋아요 내역이 있는 지 검사하는 메소드 입니다.
     *
     * @param bookId
     * @param userId
     * @return 존재 여부에 따른 boolean 값 반환
     * @author 이서연
     */
    boolean isLiked(Long bookId, Long userId);

    /**
     * 도서 별 좋아요 수를 조회하는 메소드 입니다.
     *
     * @param bookId
     * @return 권 당 좋아요 갯수
     * @author 이서연
     */
    Long countByBookId(Long bookId);

    /**
     * 회원이 누른 좋아요 목록을 조회하는 메소드입니다.
     *
     * @param userId
     * @param pageable
     * @return
     * @author: 이서연
     */
//    Page<BookInfoForLikeResponse> getAllByUser(Long userId, Pageable pageable);

}

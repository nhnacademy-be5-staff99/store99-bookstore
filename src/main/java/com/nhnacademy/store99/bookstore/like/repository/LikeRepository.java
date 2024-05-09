package com.nhnacademy.store99.bookstore.like.repository;

import com.nhnacademy.store99.bookstore.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long>, LikeRepositoryCustom {

    /**
     * 좋아요 내역이 있는 지 검사하는 메소드 입니다.
     *
     * @param bookId
     * @param userId
     * @return 존재 여부에 따른 boolean 값 반환
     * @author 이서연
     */
    Boolean existsByBookIdAndUserId(Long bookId, Long userId);


}
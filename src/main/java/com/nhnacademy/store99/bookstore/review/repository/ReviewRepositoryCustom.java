package com.nhnacademy.store99.bookstore.review.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.bind.annotation.RequestHeader;

@NoRepositoryBean
public interface ReviewRepositoryCustom {

    /**
     * 리뷰를 작성할 수 있는 지 검사하는 메소드입니다.
     *
     * @param bookId
     * @return boolean
     * @author 이서연
     */
    boolean isRegister(Long bookId, @RequestHeader("X-USER-ID") Long userId);
}

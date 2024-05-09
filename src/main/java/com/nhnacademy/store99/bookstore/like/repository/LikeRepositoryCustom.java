package com.nhnacademy.store99.bookstore.like.repository;

import com.nhnacademy.store99.bookstore.like.dto.response.BookInfoForLikeResponse;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LikeRepositoryCustom {

    /**
     * 도서 별 좋아요 수를 조회하는 메소드 입니다.
     *
     * @param bookId
     * @return SELECT COUNT(*)
     * FROM like AS l
     * JOIN book AS b
     * ON l.book_id = b.book_id;
     */
    Long countAllByBook(Long bookId);

    List<BookInfoForLikeResponse> getAllByUser(Long userId);
}

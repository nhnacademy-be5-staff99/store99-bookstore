package com.nhnacademy.store99.bookstore.like.repository;

import com.nhnacademy.store99.bookstore.like.dto.response.BookInfoForLikeResponse;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LikeRepositoryCustom {

    List<BookInfoForLikeResponse> getAllByUser(Long userId);

}

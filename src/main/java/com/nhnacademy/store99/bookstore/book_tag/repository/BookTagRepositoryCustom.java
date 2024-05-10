package com.nhnacademy.store99.bookstore.book_tag.repository;

import com.nhnacademy.store99.bookstore.book.response.BookResponse;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BookTagRepositoryCustom {

    List<BookResponse.TagDTO> findByBookId(Long bookId);
}

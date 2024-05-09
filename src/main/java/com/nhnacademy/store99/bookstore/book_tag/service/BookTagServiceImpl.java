package com.nhnacademy.store99.bookstore.book_tag.service;

import com.nhnacademy.store99.bookstore.book.response.BookResponse;
import com.nhnacademy.store99.bookstore.book_tag.repository.BookTagRepositoryCustomImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 도서 - 태그 참조 기능을 제공하는 서비스 구현체
 *
 * @author rosin23
 */

@Service
@RequiredArgsConstructor
@Transactional
public class BookTagServiceImpl implements BookTagService {

    private final BookTagRepositoryCustomImpl bookTagRepositoryCustom;

    /**
     * book id를 이용한 태그 검색
     *
     * @param bookId 조회할 book id
     * @return tag id, book id, tag name을 반환
     */

    @Override
    public List<BookResponse.TagDTO> getTagByBookId(Long bookId) {
        return bookTagRepositoryCustom.findByBookId(bookId);
    }
}

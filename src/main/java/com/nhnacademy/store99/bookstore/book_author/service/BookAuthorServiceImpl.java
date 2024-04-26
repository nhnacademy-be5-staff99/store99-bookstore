package com.nhnacademy.store99.bookstore.book_author.service;

import com.nhnacademy.store99.bookstore.book.response.BookRequest;
import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.repository.impl.BookAuthorRepositoryImpl;
import com.nhnacademy.store99.bookstore.book_author.response.BookTransDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 도서-작가 참조 기능을 제공하는 서비스 구현체
 *
 * @author yrrho2
 */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BookAuthorServiceImpl implements BookAuthorService {

    @Qualifier("bookAuthorRepositoryImpl")
    private final BookAuthorRepositoryImpl bookAuthorRepositoryImp;

    /**
     * 도서의 id를 사용하여 작가 검색
     *
     * @param bookId 도서id
     * @return 도서id, 작가id, 도서정보를 반환
     */
    @Override
    public List<BookRequest.AuthorDTO> getAuthorBook(Long bookId) {
        List<BookRequest.AuthorDTO> bookAuthor = bookAuthorRepositoryImp.getAuthorsById(bookId);
        return bookAuthor;
    }


    @Override
    public Page<BookTransDTO> getBookTransDTO(Pageable pageable) {
        Page<BookTransDTO> page = bookAuthorRepositoryImp.findBooksByIdGreaterThan(0L, pageable);
        List<BookTransDTO> list = page.getContent().stream().distinct().collect(Collectors.toList());
        return new PageImpl<>(list, page.getPageable(), list.size());
    }

    @Override
    public List<BookAuthor> getAuthorByAuthorId(Long id) {
        return null;
    }
}

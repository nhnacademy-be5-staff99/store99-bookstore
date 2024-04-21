package com.nhnacademy.store99.bookstore.book_author.service;

import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.repository.BookAuthorRepository;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorAPIResponse;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorDTO;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorName;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 도서-작가 참조 기능을 제공하는 서비스 구현체
 *
 * @author yrrho2
 */
@Transactional(readOnly = true)
@Service
public class BookAuthorServiceImp implements BookAuthorService {
    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthorServiceImp(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    /**
     * 도서의 id를 사용하여 작가 검색
     *
     * @param bookId 도서id
     * @return 도서id, 작가id, 도서정보를 반환
     */
    @Override
    public BookAuthorResponse getAuthorBook(Long bookId) {
        BookAuthor bookAuthor = bookAuthorRepository.findById(bookId).get();
        return BookAuthorResponse.builder()
                .authorName(bookAuthor.getAuthor().getAuthorName())
                .authorId(bookAuthor.getAuthor().getId())
                .bookId(bookAuthor.getBook().getId())
                .build();
    }

    @Override
    public List<BookAuthorDTO> getBookAuthorsByIdGreaterThan(Long id) {
        Iterator<BookAuthor> it = bookAuthorRepository.getBookAuthorsByIdGreaterThan(id).iterator();
        List<BookAuthorDTO> list = new ArrayList<>();
        while (it.hasNext()) {
            BookAuthor bookAuthorDTO = it.next();
            list.add(BookAuthorDTO.builder()
                    .Id(bookAuthorDTO.getId())
                    .book(bookAuthorDTO.getBook())
                    .author(bookAuthorDTO.getAuthor())
                    .build()
            );
        }
        return list;
    }

    @Override
    public List<BookAuthorAPIResponse> test() {
        return bookAuthorRepository.findBookAuthorsByIdGreaterThan(0L);
    }

    @Override
    public List<BookAuthorName> getSameIdBookAuthor() {
        return bookAuthorRepository.findBookAuthorByBookId(2L);
    }


    /**
     * 작가의 id를 사용하여 도서들을 조회합니다
     *
     * @param authorId 작가 id
     * @return BookAuthor가 담긴 리스트를 반환합니다.
     */
    @Override
    public List<BookAuthor> getAuthorByAuthorId(Long authorId) {
        return null;
    }
}

package com.nhnacademy.store99.bookstore.book_author.service;

import com.nhnacademy.store99.bookstore.author.entity.Author;
import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.response.BookWithAuthor;
import com.nhnacademy.store99.bookstore.book_author.entity.BookAuthor;
import com.nhnacademy.store99.bookstore.book_author.repository.BookAuthorRepository;
import com.nhnacademy.store99.bookstore.book_author.repository.imp.BookAuthorRepositoryImp;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorAPIResponse;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorDTO;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorName;
import com.nhnacademy.store99.bookstore.book_author.response.BookAuthorResponse;
import com.nhnacademy.store99.bookstore.book_author.response.BookPageDTO;
import com.nhnacademy.store99.bookstore.book_author.response.BookTransDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class BookAuthorServiceImp implements BookAuthorService {

    private final BookAuthorRepository bookAuthorRepository;

    @Qualifier("bookAuthorRepositoryImp")
    private final BookAuthorRepositoryImp bookAuthorRepositoryImp;

    /**
     * id 0이상의 도서들을 작가 list와 묶어서 반환
     *
     * @return Book+List<Author> Page
     */
    @Override
    public Page<BookPageDTO> getBooksAuthorName() {
        List<BookWithAuthor> book = bookAuthorRepositoryImp.findBooksByIdGreaterThanEqual(0L);
        List<BookPageDTO> booksPage = new ArrayList<>();
        Map<Book, List<Author>> booksMap = new HashMap<>();

        // Book -List<Author> Map을 만듬.
        for (BookWithAuthor bookWithAuthor : book) {
            if (!booksMap.containsKey(bookWithAuthor.getBook())) {
                ArrayList<Author> list = new ArrayList<>();
                list.add(bookWithAuthor.getAuthor());
                booksMap.put(bookWithAuthor.getBook(), list);
            } else {
                List<Author> list = booksMap.get(bookWithAuthor.getBook());
                list.add(bookWithAuthor.getAuthor());
                booksMap.replace(bookWithAuthor.getBook(), list);
            }
        }

        // Map-> Book-List<Author> DTO로 변환후 통신.
        for (Book itBook : booksMap.keySet()) {
            booksPage.add(BookPageDTO.builder()
                    .book(itBook)
                    .authors(booksMap.get(itBook))
                    .build()
            );
        }

        // map을 통신하면 좋겠지만 page에는 List만 넣어야 했다.
        return new PageImpl<>(booksPage);
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
        Iterator<BookAuthor> it = bookAuthorRepositoryImp.getBookAuthorsByIdGreaterThan(id).iterator();
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
    public List<BookAuthorAPIResponse> getBookAuthorByBooks() {
        // 0L을 Id로 넣는이유
        // 1부터 시작하는 BookAuthor Id를 전부 참조하여 가져오기 위함.

        return bookAuthorRepositoryImp.findBookAuthorsByIdGreaterThan(0L);
    }

    @Override
    public List<BookAuthorName> getSameIdBookAuthor() {
        return bookAuthorRepositoryImp.findBookAuthorByBookId(2L);
    }


    @Override
    public Page<BookTransDTO> getBookTransDTO(Pageable pageable) {
        Page<BookTransDTO> page = bookAuthorRepositoryImp.findBooksByIdGreaterThan(0L, pageable);
        List<BookTransDTO> list = page.getContent().stream().distinct().collect(Collectors.toList());
        return new PageImpl<>(list, page.getPageable(), list.size());
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

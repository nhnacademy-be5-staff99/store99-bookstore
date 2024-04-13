package com.nhnacademy.store99.bookstore.book.service;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.book.entity.BookRequest;
import com.nhnacademy.store99.bookstore.book.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 도서 서비스 구현체
 *
 * @author yrrho2
 */
@Service
public class BookServiceImp implements BookServiceInterface {
    final private BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * 도서 등록
     *
     * @param bookRequest
     * @return
     */
    @Override
    public Book postBook(BookRequest bookRequest) {
        Book book = injectBook(bookRequest);
        return bookRepository.save(book);
    }

    /**
     * id로 도서 조회
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * 모든 도서를 page로 참조하기.
     *
     * @param page
     * @return
     */
    @Override
    public Page<Book> getBooks(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdAt"));
        // size=10. 한 페이지에 보여주는 book의 수.
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return bookRepository.findAll(pageable);
    }

    /**
     * BookRequest를 새로 만드는 Book 객체에 주입하기.
     *
     * @param bookRequest
     * @return
     */
    private Book injectBook(BookRequest bookRequest) {
        return Book.builder()
                .bookIsbn13(bookRequest.getBookIsbn13())
                .bookIsbn10(bookRequest.getBookIsbn10())
                .bookTitle(bookRequest.getBookTitle())
                .bookContents(bookRequest.getBookContents())
                .bookDescription(bookRequest.getBookDescription())
                .bookPublisher(bookRequest.getBookPublisher())
                .bookDate(bookRequest.getBookDateTime())
                .bookPrice(bookRequest.getBookPrice())
                .bookSalePrice(bookRequest.getBookSalePrice())
                .bookIsPacked(bookRequest.getBookIsPacked())
                .bookThumbnailUrl(bookRequest.getBookThumbnailUrl())
                .bookStock(bookRequest.getBookStock())
                .build();
    }
}

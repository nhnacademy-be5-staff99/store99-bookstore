package com.nhnacademy.store99.bookstore.order_book.repository.impl;

import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book_image.entity.QBookImage;
import com.nhnacademy.store99.bookstore.order_book.DTO.response.IndexBookResponse;
import com.nhnacademy.store99.bookstore.order_book.entity.OrderBook;
import com.nhnacademy.store99.bookstore.order_book.entity.QOrderBook;
import com.nhnacademy.store99.bookstore.order_book.repository.OrderBookRepository;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class OrderBookRepositoryImpl extends QuerydslRepositorySupport implements OrderBookRepository {
    public OrderBookRepositoryImpl() {
        super(OrderBook.class);
    }

    // select b.*
    // from order_book ob
    // join books as b
    // where ob.book_id = b.book_id
    // GROUP BY ob.book_id
    // Order By COUNT(ob.book_id) DESC ;
    @Override
    public List<IndexBookResponse> bestBooks() {
        QBook book = QBook.book;
        QBookImage bookImage = QBookImage.bookImage;
        QOrderBook orderBook = QOrderBook.orderBook;

        List<IndexBookResponse> bestBooks = from(orderBook)
                .join(orderBook.book, book)
                .where(book.deletedAt.isNull())
                .groupBy(orderBook.book.id)
                .orderBy(orderBook.book.id.count().desc())
                .limit(5L)
                .select(Projections.bean(
                        IndexBookResponse.class,
                        book.id.as("bookId"),
                        book.bookTitle,
                        book.bookDate,
                        book.bookDescription,
                        book.bookThumbnailUrl
                )).fetch();

        // 인기있는 도서 목록을 가져오고, 그 도서의 상세 이미지는 가져올수있었음
        // 하지만 이미지를 포함하여 DTO를 만들때 인기있는 순서대로 정렬을 할수없었다.
        // 그래서 dto를 한번 더 만드는 작업을 반복해버림.
        // dto의 순서를 따로 저장하고 이미지와 함께 가져온 dto를 새로 정렬하는 방법도 있겠다.
        
        Map<Long, List<String>> bookImages = from(bookImage)
                .join(bookImage.book, book)
                .where(bookImage.book.id.in(
                        bestBooks.stream().map(IndexBookResponse::getBookId).collect(Collectors.toList())))
                .limit(5L)
                .transform(GroupBy.groupBy(book.id).as(GroupBy.list(
                        bookImage.files.fileUrl
                )));


        return bestBooks.stream().map(b -> {
            List<String> bookImageURL = bookImages.get(b.getBookId());
            return new IndexBookResponse(b.getBookId(),
                    b.getBookTitle(),
                    b.getBookDate(),
                    b.getBookDescription(),
                    b.getBookThumbnailUrl(),
                    bookImageURL.get(0));
        }).collect(Collectors.toList());
    }


    // select b.book_id, b.created_at  from books b ORDER BY b.created_at DESC, b.book_id DESC;
    @Override
    public List<IndexBookResponse> latestBooks() {
        QBook book = QBook.book;
        QBookImage bookImage = QBookImage.bookImage;

        List<Long> latestBooks = from(book)
                .where(book.deletedAt.isNull())
                .orderBy(book.bookDate.desc())
                .orderBy(book.id.asc())
                .limit(5)
                .select(book.id.as("bookId")).fetch();

        return from(bookImage)
                .join(bookImage.book, book)
                .where(bookImage.book.id.in(latestBooks))
                .orderBy(book.bookDate.desc())
                .orderBy(book.id.asc())
                .select(Projections.constructor(
                        IndexBookResponse.class,
                        book.id.as("bookId"),
                        book.bookTitle,
                        book.bookDate,
                        book.bookDescription,
                        book.bookThumbnailUrl,
                        bookImage.files.fileUrl
                )).fetch();
    }
}

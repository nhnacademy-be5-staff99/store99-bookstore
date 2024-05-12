package com.nhnacademy.store99.bookstore.order_book.repository.impl;

import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.order_book.DTO.response.IndexBookResponse;
import com.nhnacademy.store99.bookstore.order_book.DTO.response.LatestBookResponse;
import com.nhnacademy.store99.bookstore.order_book.OrderBook;
import com.nhnacademy.store99.bookstore.order_book.entity.QOrderBook;
import com.nhnacademy.store99.bookstore.order_book.repository.OrderBookRepository;
import com.querydsl.core.types.Projections;
import java.util.List;
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
        QOrderBook orderBook = QOrderBook.orderBook;

        return from(orderBook)
                .join(orderBook.book, book)
                .groupBy(orderBook.book.id)
                .orderBy(orderBook.book.id.count().desc())
                .select(Projections.constructor(
                        IndexBookResponse.class,
                        book.id.as("bookId"),
                        book.bookTitle,
                        book.bookDescription,
                        book.bookThumbnailUrl
                )).fetch();
    }


    // select b.book_id, b.created_at  from books b ORDER BY b.created_at DESC, b.book_id DESC;
    @Override
    public List<LatestBookResponse> latestBooks() {
        return null;
    }
}

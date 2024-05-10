package com.nhnacademy.store99.bookstore.order_book.repository.impl;

import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.order_book.OrderBook;
import com.nhnacademy.store99.bookstore.order_book.entity.QOrderBook;
import com.nhnacademy.store99.bookstore.order_book.repository.OrderBookRepository;
import com.nhnacademy.store99.bookstore.order_book.response.LatestBookResponse;
import com.querydsl.core.group.GroupBy;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class OrderBookRepositoryImpl extends QuerydslRepositorySupport implements OrderBookRepository {
    public OrderBookRepositoryImpl() {
        super(OrderBook.class);
    }

    @Override
    public Map<Long, Long> bestBooks() {
        QBook book = QBook.book;
        QOrderBook orderBook = QOrderBook.orderBook;

        return from(orderBook)
                .groupBy(orderBook.book.id)
                .orderBy(orderBook.book.id.count().desc())
                .transform(GroupBy.groupBy(orderBook.book.id).as(orderBook.book.id.count()));
    }


    // select b.book_id, b.created_at  from books b ORDER BY b.created_at DESC, b.book_id DESC;
    @Override
    public List<LatestBookResponse> latestBooks() {
        return null;
    }
}

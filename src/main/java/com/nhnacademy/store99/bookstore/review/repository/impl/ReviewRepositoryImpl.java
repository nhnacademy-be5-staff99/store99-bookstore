package com.nhnacademy.store99.bookstore.review.repository.impl;

import com.nhnacademy.store99.bookstore.consumer.entity.QConsumer;
import com.nhnacademy.store99.bookstore.order.entity.QOrder;
import com.nhnacademy.store99.bookstore.order_book.QOrderBook;
import com.nhnacademy.store99.bookstore.review.entity.Review;
import com.nhnacademy.store99.bookstore.review.repository.ReviewRepositoryCustom;
import com.nhnacademy.store99.bookstore.user.entity.QUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ReviewRepositoryImpl extends QuerydslRepositorySupport implements ReviewRepositoryCustom {

    public ReviewRepositoryImpl() {
        super(Review.class);
    }

    @Override
    public boolean isRegister(Long bookId, Long userId) {
        QUser user = QUser.user;
        QConsumer consumer = QConsumer.consumer;
        QOrder order = QOrder.order;
        QOrderBook orderBook = QOrderBook.orderBook;

        Long result = from(user)
                .innerJoin(consumer).on(user.consumers.id.eq(consumer.id))
                .innerJoin(order).on(consumer.id.eq(order.consumerId))
                .innerJoin(orderBook).on(order.orderId.eq(orderBook.order.orderId))
                .where(orderBook.book.id.eq(bookId))
                .select()
                .fetchCount();

        return result >= 1;
    }
}

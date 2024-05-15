package com.nhnacademy.store99.bookstore.order.repository.impl;

import static com.querydsl.core.group.GroupBy.groupBy;

import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.consumer.entity.QConsumer;
import com.nhnacademy.store99.bookstore.order.dto.response.BookInOrderInquiryResponse;
import com.nhnacademy.store99.bookstore.order.dto.response.OrderInquiryResponse;
import com.nhnacademy.store99.bookstore.order.entity.Order;
import com.nhnacademy.store99.bookstore.order.entity.QOrder;
import com.nhnacademy.store99.bookstore.order.repository.OrderRepositoryCustom;
import com.nhnacademy.store99.bookstore.order_book.entity.QOrderBook;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * @author seunggyu-kim
 */
public class OrderRepositoryImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {
    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public OrderInquiryResponse getOrderByGuest(final String orderId, final String orderPassword) {
        QConsumer consumer = QConsumer.consumer;
        QOrder order = QOrder.order;
        QOrderBook orderBook = QOrderBook.orderBook;
        QBook book = QBook.book;

        return from(order)
                .innerJoin(order.consumer, consumer)
                .innerJoin(orderBook).on(order.orderId.eq(orderBook.order.orderId))
                .innerJoin(orderBook.book, book)
                .where(order.orderId.eq(orderId)
                        .and(consumer.consumerPassword.eq(orderPassword)))
                .transform(groupBy(order.orderId).as(Projections.constructor(OrderInquiryResponse.class,
                        order.orderId,
                        order.orderState,
                        order.orderReleaseAt,
                        order.orderTotalCost,
                        order.orderDeliveryCost,
                        order.orderUsePoint,
                        order.couponDiscount,
                        order.orderFinalCost,
                        GroupBy.list(Projections.constructor(BookInOrderInquiryResponse.class,
                                orderBook.book.id,
                                orderBook.orderBookTitle,
                                orderBook.orderBookQuantity,
                                orderBook.orderBookPrice
                        )),
                        consumer.consumerName,
                        consumer.consumerPhone,
                        consumer.consumerEmail,
                        order.orderReceiverName,
                        order.orderReceiverNumber,
                        order.orderAddress,
                        order.orderAddressCode,
                        order.orderAddressDetail
                ))).get(orderId);
    }
}

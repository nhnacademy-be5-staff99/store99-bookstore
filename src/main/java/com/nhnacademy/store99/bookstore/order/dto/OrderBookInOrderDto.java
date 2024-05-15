package com.nhnacademy.store99.bookstore.order.dto;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.order.entity.Order;
import com.nhnacademy.store99.bookstore.order_book.entity.OrderBook;
import java.util.Objects;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author seunggyu-kim
 */
@Getter
public class OrderBookInOrderDto {
    private String orderBookTitle;

    private Integer orderBookPrice;

    private Integer orderBookQuantity;

//    private Integer orderBookWrapperCost;

//    private Wrapper wrapper;

    private Long bookId;

    public OrderBook toEntity(@NonNull final Book book, @NonNull final Order order) {
        if (!Objects.equals(book.getId(), bookId)) {
            throw new IllegalArgumentException("bookId is not matched");
        }
        return OrderBook.builder()
                .orderBookTitle(orderBookTitle)
                .orderBookPrice(orderBookPrice)
                .orderBookQuantity(orderBookQuantity)
                .book(book)
                .order(order)
                .build();
    }
}

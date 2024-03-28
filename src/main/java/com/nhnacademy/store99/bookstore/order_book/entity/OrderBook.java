package com.nhnacademy.store99.bookstore.order_book.entity;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.order.entity.Order;
import com.nhnacademy.store99.bookstore.wrapper.entity.Wrapper;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_book", schema = "staff99_bookstore")
public class OrderBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_book_id", nullable = false)
    private Long id;

    @Column(name = "order_book_price", nullable = false)
    private Integer orderBookPrice;

    @Column(name = "order_book_quantity", nullable = false)
    private Integer orderBookQuantity;

    @Column(name = "order_book_wrapper_cost")
    private Integer orderBookWrapperCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wrapper_id")
    private Wrapper wrapper;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
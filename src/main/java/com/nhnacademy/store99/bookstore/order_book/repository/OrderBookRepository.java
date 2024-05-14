package com.nhnacademy.store99.bookstore.order_book.repository;

import com.nhnacademy.store99.bookstore.order_book.entity.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author seunggyu-kim
 */
public interface OrderBookRepository extends JpaRepository<OrderBook, Long>, OrderBookRepositoryCustom {

}



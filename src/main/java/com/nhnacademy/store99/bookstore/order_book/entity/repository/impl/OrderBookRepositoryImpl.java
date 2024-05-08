package com.nhnacademy.store99.bookstore.order_book.entity.repository.impl;

import com.nhnacademy.store99.bookstore.order_book.entity.repository.OrderBookRepository;
import com.nhnacademy.store99.bookstore.order_book.entity.response.BestBookResponse;
import com.nhnacademy.store99.bookstore.order_book.entity.response.LatestBookResponse;
import java.util.List;

public class OrderBookRepositoryImpl implements OrderBookRepository {
    @Override
    public List<BestBookResponse> bestBooks() {
        return null;
    }

    @Override
    public List<LatestBookResponse> latestBooks() {
        return null;
    }
}

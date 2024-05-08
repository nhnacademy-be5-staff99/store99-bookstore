package com.nhnacademy.store99.bookstore.order_book.entity.service.impl;

import com.nhnacademy.store99.bookstore.order_book.entity.repository.OrderBookRepository;
import com.nhnacademy.store99.bookstore.order_book.entity.response.LatestBookResponse;
import com.nhnacademy.store99.bookstore.order_book.entity.service.OrderBookService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderBookServiceImpl implements OrderBookService {
    private final OrderBookRepository orderBookRepository;

    @Override
    public Map<Long, Long> bestBooks() {
        return orderBookRepository.bestBooks();
    }

    @Override
    public List<LatestBookResponse> latestBooks() {
        return null;
    }
}

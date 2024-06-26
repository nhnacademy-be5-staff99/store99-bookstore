package com.nhnacademy.store99.bookstore.order_book.service.impl;

import com.nhnacademy.store99.bookstore.order_book.DTO.response.IndexBookResponse;
import com.nhnacademy.store99.bookstore.order_book.repository.OrderBookRepository;
import com.nhnacademy.store99.bookstore.order_book.service.OrderBookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderBookServiceImpl implements OrderBookService {
    private final OrderBookRepository orderBookRepository;

    @Override
    public List<IndexBookResponse> bestBooks() {
        return orderBookRepository.bestBooks();
    }

    @Override
    public List<IndexBookResponse> latestBooks() {
        return orderBookRepository.latestBooks();
    }
}

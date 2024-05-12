package com.nhnacademy.store99.bookstore.order_book.controller;

import com.nhnacademy.store99.bookstore.order_book.DTO.response.IndexBookResponse;
import com.nhnacademy.store99.bookstore.order_book.service.OrderBookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open/v1/books")
@RequiredArgsConstructor
public class OrderBookController {
    private final OrderBookService orderBookService;

    @GetMapping("/best")
    public List<IndexBookResponse> getBestBooks() {
        return orderBookService.bestBooks();
    }
}

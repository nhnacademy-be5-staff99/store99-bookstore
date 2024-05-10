package com.nhnacademy.store99.bookstore.order_book.entity.controller;

import com.nhnacademy.store99.bookstore.order_book.entity.service.OrderBookService;
import java.util.Map;
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
    public Map<Long, Long> getOB() {
        return orderBookService.bestBooks();
    }
}

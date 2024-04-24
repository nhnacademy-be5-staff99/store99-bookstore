package com.nhnacademy.store99.bookstore.cart.controller;

import com.nhnacademy.store99.bookstore.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.bookstore.cart.service.CartService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author seunggyu-kim
 */
@Controller
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBookToCart(@RequestBody @Valid CartItemRequest request) {
        cartService.addBookToCart(request);
    }
}

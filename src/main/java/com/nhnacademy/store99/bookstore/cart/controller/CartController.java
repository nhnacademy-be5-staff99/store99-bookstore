package com.nhnacademy.store99.bookstore.cart.controller;

import com.nhnacademy.store99.bookstore.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.bookstore.cart.dto.response.CartItemResponse;
import com.nhnacademy.store99.bookstore.cart.service.CartQueryService;
import com.nhnacademy.store99.bookstore.cart.service.CartService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author seunggyu-kimpost
 */
@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartQueryService cartQueryService;

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBookToCart(@RequestBody @Valid CartItemRequest request) {
        cartService.addBookToCart(request);
    }

    @GetMapping("/books")
    public List<CartItemResponse> getCartItemsByUser() {
        return cartQueryService.getCartItemsByUser();
    }
}

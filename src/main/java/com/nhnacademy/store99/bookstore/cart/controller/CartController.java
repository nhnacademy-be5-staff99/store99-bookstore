package com.nhnacademy.store99.bookstore.cart.controller;

import com.nhnacademy.store99.bookstore.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.bookstore.cart.dto.response.CartItemResponse;
import com.nhnacademy.store99.bookstore.cart.service.CartQueryService;
import com.nhnacademy.store99.bookstore.cart.service.CartService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author seunggyu-kimpost
 */
@RestController
@RequestMapping("/v1/cart/books")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartQueryService cartQueryService;

    @PostMapping
    public ResponseEntity<Void> addBookToCart(@RequestBody @Valid CartItemRequest request) {
        cartService.addBookToCart(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<CartItemResponse> getCartItemsByUser() {
        return cartQueryService.getCartItemsByUser();
    }

    @PutMapping
    public void modifyBookQuantityInCart(@RequestBody @Valid CartItemRequest request) {
        cartService.modifyBookQuantityInCart(request);
    }

    @DeleteMapping("/{bookId}")
    public void removeBookFromCart(@PathVariable Long bookId) {
        cartService.removeBookInCart(bookId);
    }

    @PostMapping("/merge")
    public void mergeCart(@RequestBody Map<Long, Integer> bookIdAndQuantity) {
        cartService.mergeCart(bookIdAndQuantity);
    }
}

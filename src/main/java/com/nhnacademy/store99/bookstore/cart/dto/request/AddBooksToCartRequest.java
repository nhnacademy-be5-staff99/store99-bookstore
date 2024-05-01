package com.nhnacademy.store99.bookstore.cart.dto.request;

import java.util.List;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
public class AddBooksToCartRequest {
    private List<CartItemRequest> cartItemList;
}

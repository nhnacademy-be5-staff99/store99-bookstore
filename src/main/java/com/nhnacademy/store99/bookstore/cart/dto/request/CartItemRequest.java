package com.nhnacademy.store99.bookstore.cart.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
public class CartItemRequest {
    @Min(1)
    @Max(100)
    @NotNull
    private final Integer quantity = 1;
    @NotNull
    private Long bookId;
}

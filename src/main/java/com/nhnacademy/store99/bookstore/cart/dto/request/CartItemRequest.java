package com.nhnacademy.store99.bookstore.cart.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
@AllArgsConstructor
public class CartItemRequest {
    @NotNull
    private Long bookId;

    @Min(1)
    @Max(100)
    @NotNull
    private Integer quantity = 1;
}

package com.nhnacademy.store99.bookstore.category.dto.request;

import javax.validation.constraints.NotNull;

/**
 * 카테고리 제거 요청 DTO
 *
 * @author seunggyu-kim
 */
public class RemoveCategoryRequest {
    @NotNull
    private Long id;
}

package com.nhnacademy.store99.bookstore.category.dto.request;

import javax.validation.constraints.NotNull;

/**
 * 카테고리 수정 요청 DTO
 *
 * @author seunggyu-kim
 */
public class ModifyCategoryRequest {
    @NotNull
    private Long id;

    @NotNull
    private String categoryName;

    private String parentCategoryId;
}

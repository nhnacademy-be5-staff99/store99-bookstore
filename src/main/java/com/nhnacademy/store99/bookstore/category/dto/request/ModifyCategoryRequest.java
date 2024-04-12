package com.nhnacademy.store99.bookstore.category.dto.request;

/**
 * 카테고리 수정 요청 DTO
 *
 * @author seunggyu-kim
 */
public class ModifyCategoryRequest {
    private Long id;

    private String categoryName;

    private String parentCategoryId;
}

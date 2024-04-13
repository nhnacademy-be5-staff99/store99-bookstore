package com.nhnacademy.store99.bookstore.category.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 카테고리 추가 요청 DTO
 *
 * @author seunggyu-kim
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryRequest {
    private String categoryName;

    private Integer categoryDepth;

    private Long parentCategoryId;
}

package com.nhnacademy.store99.bookstore.category.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 카테고리 추가 요청 DTO
 *
 * @author seunggyu-kim
 */
@Getter
@AllArgsConstructor
public class AddCategoryRequest {
    @NotNull
    private String categoryName;

    private Long parentCategoryId;
}

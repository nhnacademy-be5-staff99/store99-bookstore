package com.nhnacademy.store99.bookstore.category.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private String categoryName;

    @Max(10)
    @NotNull
    private Integer categoryDepth;

    private Long parentCategoryId;
}

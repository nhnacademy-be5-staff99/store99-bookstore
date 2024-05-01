package com.nhnacademy.store99.bookstore.category.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 카테고리 수정 요청 DTO
 *
 * @author seunggyu-kim
 */
@Getter
@AllArgsConstructor
public class ModifyCategoryRequest {
    @NotBlank
    private String categoryName;

    private Long parentCategoryId;
}

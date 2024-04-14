package com.nhnacademy.store99.bookstore.category.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 카테고리 제거 요청 DTO
 *
 * @author seunggyu-kim
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveCategoryRequest {
    @NotNull
    private Long id;
}

package com.nhnacademy.store99.bookstore.category.dto.response;

import com.nhnacademy.store99.bookstore.category.entity.Category;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 카테고리 조회 응답 DTO
 *
 * @author seunggyu-kim
 */
@Getter
@Builder
@AllArgsConstructor
public class CategoryForAdminResponse {
    private Long id;

    private String categoryName;

    private Integer categoryDepth;

    private Long parentCategoryId;

    private LocalDateTime deletedAt;

    public static CategoryForAdminResponse from(Category category) {
        return CategoryForAdminResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .categoryDepth(category.getCategoryDepth())
                .parentCategoryId(
                        Objects.nonNull(category.getParentCategory()) ? category.getParentCategory().getId() : null)
                .deletedAt(category.getDeletedAt())
                .build();
    }
}

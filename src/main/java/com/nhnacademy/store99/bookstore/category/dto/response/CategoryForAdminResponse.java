package com.nhnacademy.store99.bookstore.category.dto.response;

import com.nhnacademy.store99.bookstore.category.entity.Category;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 카테고리 조회 응답 DTO
 *
 * @author seunggyu-kim
 */
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForAdminResponse {
    private Long id;

    private String categoryName;

    private Integer categoryDepth;

    private Long parentCategoryId;

    private LocalDateTime deletedAt;

    public static CategoryForAdminResponse from(Category category) {
        return new CategoryForAdminResponse(
                category.getId(),
                category.getCategoryName(),
                category.getCategoryDepth(),
                Objects.nonNull(category.getParentCategory()) ? category.getParentCategory().getId() : null,
                category.getDeletedAt()
        );
    }
}

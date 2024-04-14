package com.nhnacademy.store99.bookstore.category.dto.response;

import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 카테고리 조회 응답 DTO
 *
 * @author seunggyu-kim
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForAdminResponse {
    private Long id;

    private String categoryName;

    @Max(10)
    @NotNull
    private Integer categoryDepth;

    private Long parentCategoryId;

    private LocalDateTime deletedAt;
}

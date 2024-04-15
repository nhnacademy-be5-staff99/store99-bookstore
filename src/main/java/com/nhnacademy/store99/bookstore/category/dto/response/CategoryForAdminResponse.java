package com.nhnacademy.store99.bookstore.category.dto.response;

import java.time.LocalDateTime;
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

//    private Long parentCategoryId;

    private LocalDateTime deletedAt;
}

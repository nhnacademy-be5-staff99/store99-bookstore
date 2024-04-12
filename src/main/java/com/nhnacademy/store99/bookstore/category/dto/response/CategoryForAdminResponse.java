package com.nhnacademy.store99.bookstore.category.dto.response;

import java.time.LocalDateTime;

/**
 * 카테고리 조회 응답 DTO
 *
 * @author seunggyu-kim
 */
public class CategoryForAdminResponse {
    private Long id;

    private String categoryName;

    private Long parentCategoryId;

    private LocalDateTime deletedAt;
}

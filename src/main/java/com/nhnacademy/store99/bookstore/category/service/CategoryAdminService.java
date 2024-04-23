package com.nhnacademy.store99.bookstore.category.service;

import com.nhnacademy.store99.bookstore.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 관리자 카테고리 관리 서비스 인터페이스
 *
 * @author seunggyu-kim
 */
public interface CategoryAdminService {
    Page<CategoryForAdminResponse> getCategories(Pageable pageable);

    Long addCategoryAndGetId(AddCategoryRequest request) throws NotFoundException;

    void modifyCategory(Long categoryId, ModifyCategoryRequest request);

    void removeCategory(final Long categoryId);

    void restoreCategory(final Long categoryId);
}

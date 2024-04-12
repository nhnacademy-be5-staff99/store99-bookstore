package com.nhnacademy.store99.bookstore.category.service;

import com.nhnacademy.store99.bookstore.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.request.RemoveCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.bookstore.category.entity.Category;
import org.springframework.data.domain.Page;

/**
 * 관리자 카테고리 관리 서비스 인터페이스
 *
 * @author seunggyu-kim
 */
public interface CategoryAdminService {
    Page<CategoryForAdminResponse> getCategories();

    Category addCategory(AddCategoryRequest request);

    void modifyCategory(ModifyCategoryRequest request);

    void removeCategory(RemoveCategoryRequest request);
}

package com.nhnacademy.store99.bookstore.category.service.impl;

import com.nhnacademy.store99.bookstore.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.request.RemoveCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.bookstore.category.entity.Category;
import com.nhnacademy.store99.bookstore.category.exception.CategoryNotFoundException;
import com.nhnacademy.store99.bookstore.category.repository.CategoryRepository;
import com.nhnacademy.store99.bookstore.category.service.CategoryAdminService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 관리자 카테고리 관리 서비스 구현체
 *
 * @author seunggyu-kim
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CategoryAdminServiceImpl implements CategoryAdminService {
    private final CategoryRepository categoryRepository;

    @Override
    public Page<CategoryForAdminResponse> getCategories() {
        return null;        // TODO
    }

    @Transactional
    @Override
    public Long addCategoryAndGetId(final AddCategoryRequest request) throws CategoryNotFoundException {
        Category parentCategory = null;
        if (Objects.nonNull(request.getParentCategoryId())) {
            parentCategory = categoryRepository.findById(request.getParentCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException(request.getParentCategoryId()));
        }

        Category category = Category.builder()
                .categoryName(request.getCategoryName())
                .categoryDepth(request.getCategoryDepth())
                .parentCategory(parentCategory)
                .build();

        return categoryRepository.save(category).getId();
    }

    @Override
    public void modifyCategory(final ModifyCategoryRequest request) {
        // TODO
    }

    @Override
    public void removeCategory(final RemoveCategoryRequest request) {
        // TODO
    }
}

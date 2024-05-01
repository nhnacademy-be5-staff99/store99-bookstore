package com.nhnacademy.store99.bookstore.category.service.impl;

import com.nhnacademy.store99.bookstore.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.bookstore.category.entity.Category;
import com.nhnacademy.store99.bookstore.category.exception.CategoryNotFoundException;
import com.nhnacademy.store99.bookstore.category.repository.CategoryRepository;
import com.nhnacademy.store99.bookstore.category.service.CategoryAdminService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<CategoryForAdminResponse> getCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(CategoryForAdminResponse::from);
    }

    @Transactional
    @Override
    public Long addCategoryAndGetId(final AddCategoryRequest request) throws CategoryNotFoundException {
        Category parentCategory = null;
        if (Objects.nonNull(request.getParentCategoryId())) {
            parentCategory = categoryRepository.findById(request.getParentCategoryId()).orElse(null);
        }

        Category category = Category.builder()
                .categoryName(request.getCategoryName())
                .categoryDepth(Objects.nonNull(parentCategory) ? parentCategory.getCategoryDepth() + 1 : 1)
                .parentCategory(parentCategory)
                .build();

        return categoryRepository.save(category).getId();
    }

    @Override
    @Transactional
    public void modifyCategory(final Long categoryId, final ModifyCategoryRequest request) {
        categoryRepository.findById(categoryId).ifPresent(category -> {
            Category parentCategory = categoryRepository.findById(request.getParentCategoryId()).orElse(null);
            category.modify(request.getCategoryName(), parentCategory);
        });
    }

    @Override
    @Transactional
    public void removeCategory(final Long categoryId) {
        categoryRepository.findById(categoryId).ifPresent(Category::delete);
    }

    @Override
    @Transactional
    public void restoreCategory(final Long categoryId) {
        categoryRepository.findById(categoryId).ifPresent(Category::restore);
    }
}

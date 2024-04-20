package com.nhnacademy.store99.bookstore.category.service.impl;

import com.nhnacademy.store99.bookstore.category.dto.response.CategoryChildrenListAndRouteResponse;
import com.nhnacademy.store99.bookstore.category.repository.CategoryRepository;
import com.nhnacademy.store99.bookstore.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryChildrenListAndRouteResponse getChildrenListAndRoute(final Long categoryId) {
        CategoryChildrenListAndRouteResponse response = new CategoryChildrenListAndRouteResponse();
        categoryRepository.findById(categoryId).ifPresent(response::setNowCategoryRoute);
        response.setChildrenCategoryList(categoryRepository.getCategoriesByParentCategory_IdAndDeletedAtIsNull(categoryId));
        return response;
    }
}

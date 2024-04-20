package com.nhnacademy.store99.bookstore.category.service.impl;

import com.nhnacademy.store99.bookstore.category.dto.response.ActiveCategoryResponse;
import com.nhnacademy.store99.bookstore.category.repository.CategoryRepository;
import com.nhnacademy.store99.bookstore.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<ActiveCategoryResponse> getActiveCategories(final Integer depth) {
        return categoryRepository.getCategoriesByCategoryDepth(depth);
    }
}

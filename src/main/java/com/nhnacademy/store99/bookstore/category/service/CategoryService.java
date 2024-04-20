package com.nhnacademy.store99.bookstore.category.service;

import com.nhnacademy.store99.bookstore.category.dto.response.ActiveCategoryResponse;
import java.util.List;

public interface CategoryService {
    List<ActiveCategoryResponse> getActiveCategories(final Integer depth);
}

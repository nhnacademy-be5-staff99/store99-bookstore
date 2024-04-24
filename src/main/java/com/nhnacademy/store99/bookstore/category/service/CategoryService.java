package com.nhnacademy.store99.bookstore.category.service;

import com.nhnacademy.store99.bookstore.category.dto.response.CategoryChildrenListAndRouteResponse;

/**
 * @author seunggyu-kim
 */
public interface CategoryService {
    CategoryChildrenListAndRouteResponse getChildrenListAndRoute(final Long categoryId);
}

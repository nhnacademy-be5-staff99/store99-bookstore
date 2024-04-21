package com.nhnacademy.store99.bookstore.category.controller;

import com.nhnacademy.store99.bookstore.category.dto.response.CategoryChildrenListAndRouteResponse;
import com.nhnacademy.store99.bookstore.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author seunggyu-kim
 */
@RestController
@RequestMapping("/open/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(params = "categoryId")
    public CategoryChildrenListAndRouteResponse getCategoryChildrenListAndRoute(@RequestParam Long categoryId) {
        return categoryService.getChildrenListAndRoute(categoryId);
    }
}

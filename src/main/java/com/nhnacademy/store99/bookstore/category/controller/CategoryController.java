package com.nhnacademy.store99.bookstore.category.controller;

import com.nhnacademy.store99.bookstore.category.dto.response.ActiveCategoryResponse;
import com.nhnacademy.store99.bookstore.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    // 203
    @GetMapping(params = "depth")
    public List<ActiveCategoryResponse> getActiveCategories(@RequestParam Integer depth) {
        return categoryService.getActiveCategories(depth);
    }
}

package com.nhnacademy.store99.bookstore.category.exception;

import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;

/**
 * @author seunggyu-kim
 */
public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(Long categoryId) {
        super(String.format("Category not found (categoryId: %d)", categoryId));
    }
}

package com.nhnacademy.store99.bookstore.book_category.service;

import com.nhnacademy.store99.bookstore.book_category.response.CategoryParentsDTO;
import java.util.List;

public interface BookCategoryService {
    List<CategoryParentsDTO> getBooksByCategory(Long categoryId);
}

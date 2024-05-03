package com.nhnacademy.store99.bookstore.book_category.service;

import com.nhnacademy.store99.bookstore.book_category.response.BookCategoryResponse;
import java.util.List;

public interface BookCategoryService {
    List<BookCategoryResponse> getBooksByCategory(Long categoryId);
}

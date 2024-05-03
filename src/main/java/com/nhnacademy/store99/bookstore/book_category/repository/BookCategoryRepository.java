package com.nhnacademy.store99.bookstore.book_category.repository;

import com.nhnacademy.store99.bookstore.book_category.response.BookCategoryResponse;
import java.util.List;

public interface BookCategoryRepository {
    List<BookCategoryResponse> getBooksByCategory(Long categoryId);
}

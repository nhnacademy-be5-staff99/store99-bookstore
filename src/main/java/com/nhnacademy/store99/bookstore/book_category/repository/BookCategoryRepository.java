package com.nhnacademy.store99.bookstore.book_category.repository;

import com.nhnacademy.store99.bookstore.book_category.response.BookCategoryResponse;
import com.nhnacademy.store99.bookstore.book_category.response.CategoryParentsDTO;
import java.util.List;

public interface BookCategoryRepository {
    List<CategoryParentsDTO> getCategoriesByParentsId(Long categoryId);

    List<BookCategoryResponse> getBooksByCategories();
}

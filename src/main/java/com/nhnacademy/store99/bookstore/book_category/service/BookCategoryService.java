package com.nhnacademy.store99.bookstore.book_category.service;

import com.nhnacademy.store99.bookstore.book.dto.response.BookListElementDTO;
import com.nhnacademy.store99.bookstore.book_category.dto.response.CategoryParentsDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookCategoryService {
    List<CategoryParentsDTO> getBooksByCategory(Long categoryId);

    Page<BookListElementDTO> getBooksByCategories(Long categoryId, Pageable pageable);
}

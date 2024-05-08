package com.nhnacademy.store99.bookstore.book_category.service;

import com.nhnacademy.store99.bookstore.book_author.response.BookTransDTO;
import com.nhnacademy.store99.bookstore.book_category.response.CategoryParentsDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookCategoryService {
    List<CategoryParentsDTO> getBooksByCategory(Long categoryId);

    Page<BookTransDTO> getBooksByCategories(List<CategoryParentsDTO> parentsDTOList, Pageable pageable);
}

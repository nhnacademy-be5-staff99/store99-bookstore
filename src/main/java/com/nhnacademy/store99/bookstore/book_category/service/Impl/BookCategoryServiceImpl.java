package com.nhnacademy.store99.bookstore.book_category.service.Impl;

import com.nhnacademy.store99.bookstore.book_author.response.BookTransDTO;
import com.nhnacademy.store99.bookstore.book_category.repository.BookCategoryRepository;
import com.nhnacademy.store99.bookstore.book_category.response.CategoryParentsDTO;
import com.nhnacademy.store99.bookstore.book_category.service.BookCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;

    @Override
    public List<CategoryParentsDTO> getBooksByCategory(Long categoryId) {
        return bookCategoryRepository.getCategoriesByParentsId(categoryId);
    }

    @Override
    public Page<BookTransDTO> getBooksByCategories(List<CategoryParentsDTO> parentsDTOList, Pageable pageable) {
        return bookCategoryRepository.getBooksByCategories(parentsDTOList, pageable);
    }
}

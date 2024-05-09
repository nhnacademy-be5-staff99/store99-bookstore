package com.nhnacademy.store99.bookstore.book_category.service.Impl;

import com.nhnacademy.store99.bookstore.book.response.BookListElementDTO;
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
    public Page<BookListElementDTO> getBooksByCategories(Long categoryId, Pageable pageable) {
        List<CategoryParentsDTO> cp = bookCategoryRepository.getCategoriesByParentsId(categoryId);
        return bookCategoryRepository.getBooksByCategories(cp, pageable);
    }
}

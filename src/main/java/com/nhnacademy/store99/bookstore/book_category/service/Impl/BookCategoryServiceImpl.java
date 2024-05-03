package com.nhnacademy.store99.bookstore.book_category.service.Impl;

import com.nhnacademy.store99.bookstore.book_category.repository.BookCategoryRepository;
import com.nhnacademy.store99.bookstore.book_category.response.BookCategoryResponse;
import com.nhnacademy.store99.bookstore.book_category.service.BookCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;

    @Override
    public List<BookCategoryResponse> getBooksByCategory(Long categoryId) {
        return bookCategoryRepository.getCategoriesByParentsId(categoryId);
    }
}

package com.nhnacademy.store99.bookstore.book_category.service.Impl;

import com.nhnacademy.store99.bookstore.book_category.response.BookCategoryResponse;
import com.nhnacademy.store99.bookstore.book_category.service.BookCategoryService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {
    @Override
    public List<BookCategoryResponse> getBooksByCategory(Long categoryId) {
        return null;
    }
}

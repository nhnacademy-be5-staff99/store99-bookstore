package com.nhnacademy.store99.bookstore.book_category.repository.Impl;

import com.nhnacademy.store99.bookstore.book_category.entity.BookCategory;
import com.nhnacademy.store99.bookstore.book_category.repository.BookCategoryRepository;
import com.nhnacademy.store99.bookstore.book_category.response.BookCategoryResponse;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BookCategoryRepositoryImpl extends QuerydslRepositorySupport implements BookCategoryRepository {
    public BookCategoryRepositoryImpl() {
        super(BookCategory.class);
    }

    @Override
    public List<BookCategoryResponse> getBooksByCategory(Long categoryId) {
        return null;
    }
}

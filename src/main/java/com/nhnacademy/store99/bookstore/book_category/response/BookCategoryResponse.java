package com.nhnacademy.store99.bookstore.book_category.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookCategoryResponse {
    Long categoryId;
    String categoryName;
    int categoryDepth;
    int parentCategoryId;
}

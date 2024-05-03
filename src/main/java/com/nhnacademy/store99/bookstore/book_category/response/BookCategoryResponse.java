package com.nhnacademy.store99.bookstore.book_category.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookCategoryResponse {
    Long categoryId;
    String categoryName;
    int categoryDepth;
    Long parentCategoryId;
}

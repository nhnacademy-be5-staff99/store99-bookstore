package com.nhnacademy.store99.bookstore.book_category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryParentsDTO {
    Long categoryId;
    Long parentCategoryId;
}

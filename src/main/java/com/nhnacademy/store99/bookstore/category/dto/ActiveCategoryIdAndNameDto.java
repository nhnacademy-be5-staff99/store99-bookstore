package com.nhnacademy.store99.bookstore.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActiveCategoryIdAndNameDto {
    private Long id;
    private String categoryName;
}

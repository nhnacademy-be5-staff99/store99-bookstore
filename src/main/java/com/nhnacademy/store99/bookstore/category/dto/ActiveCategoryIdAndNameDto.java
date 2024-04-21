package com.nhnacademy.store99.bookstore.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author seunggyu-kim
 */
@ToString
@Getter
@AllArgsConstructor
public class ActiveCategoryIdAndNameDto {
    private Long id;
    private String categoryName;
}

package com.nhnacademy.store99.bookstore.book_category.controller;

import com.nhnacademy.store99.bookstore.book.dto.response.BookListElementDTO;
import com.nhnacademy.store99.bookstore.book_category.dto.response.CategoryParentsDTO;
import com.nhnacademy.store99.bookstore.book_category.service.BookCategoryService;
import com.nhnacademy.store99.bookstore.order_book.DTO.response.IndexBookResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open/v1/books")
@RequiredArgsConstructor
public class BookCategoryController {
    final private BookCategoryService bookCategoryService;

    // 자식 카테고리들 반환. 당장 사용하지 않음
    @GetMapping("/categories/child/{categoryId}")
    public List<CategoryParentsDTO> getCB(@PathVariable("categoryId") Long categoryId) {
        return bookCategoryService.getBooksByCategory(categoryId);
    }


    // 자식 카테고리까지 포함된 도서 목록
    @GetMapping("/categories/{categoryId}")
    public Page<BookListElementDTO> getBooksByCa(@PathVariable("categoryId") Long categoryId, Pageable pageable) {
        return bookCategoryService.getBooksByCategories(
                categoryId,
                pageable
        );
    }

    @GetMapping("/categories/index")
    public List<IndexBookResponse> getBooksByCa(@RequestParam("categoryId") Long categoryId) {
        return bookCategoryService.getBooksByCategories(categoryId);
    }
}

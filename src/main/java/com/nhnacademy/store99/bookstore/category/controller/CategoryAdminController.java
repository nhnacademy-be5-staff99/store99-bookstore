package com.nhnacademy.store99.bookstore.category.controller;

import com.nhnacademy.store99.bookstore.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.bookstore.category.service.CategoryAdminService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 관리자 카테고리 관리 API 컨트롤러
 *
 * @author seunggyu-kim
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/v1/categories")
public class CategoryAdminController {
    private final CategoryAdminService categoryAdminService;

    @GetMapping
    public Page<CategoryForAdminResponse> getCategories(Pageable pageable) {
        return categoryAdminService.getCategories(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<CommonResponse<Void>> addCategory(@RequestBody @Valid AddCategoryRequest request) {
        Long categoryId = categoryAdminService.addCategoryAndGetId(request);

        CommonHeader header = CommonHeader.builder().httpStatus(HttpStatus.CREATED).build();
        CommonResponse<Void> response = CommonResponse.<Void>builder().header(header).build();

        return ResponseEntity.created(URI.create("/admin/v1/categories/" + categoryId)).body(response);
    }

    @PutMapping("/{categoryId}")
    public void modifyCategory(@PathVariable Long categoryId, @RequestBody @Valid ModifyCategoryRequest request) {
        categoryAdminService.modifyCategory(categoryId, request);
    }

    @DeleteMapping("/{categoryId}")
    public void removeCategory(@PathVariable Long categoryId) {
        categoryAdminService.removeCategory(categoryId);
    }

    @PutMapping("/{categoryId}/restore")
    public void restoreCategory(@PathVariable Long categoryId) {
        categoryAdminService.restoreCategory(categoryId);
    }
}

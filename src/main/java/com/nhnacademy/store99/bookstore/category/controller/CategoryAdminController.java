package com.nhnacademy.store99.bookstore.category.controller;

import com.nhnacademy.store99.bookstore.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.request.RemoveCategoryRequest;
import com.nhnacademy.store99.bookstore.category.service.CategoryAdminService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 관리자 카테고리 관리 API 컨트롤러
 *
 * @author seunggyu-kim
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/v1/categories")
public class CategoryAdminController {
    private final CategoryAdminService categoryAdminService;

    @GetMapping
    public void viewAdminCategoryManagementPage() {
        // TODO
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<CommonResponse<Void>> addCategory(@ModelAttribute @Valid AddCategoryRequest request) {
        Long categoryId = categoryAdminService.addCategoryAndGetId(request);

        CommonHeader header = CommonHeader.builder().httpStatus(HttpStatus.CREATED).build();
        CommonResponse<Void> response = CommonResponse.<Void>builder().header(header).build();

        return ResponseEntity.created(URI.create("/admin/v1/categories/" + categoryId)).body(response);
    }

    @PutMapping
    public void modifyCategory(@ModelAttribute @Valid ModifyCategoryRequest request) {
        // TODO
    }

    @DeleteMapping
    public void removeCategory(@ModelAttribute @Valid RemoveCategoryRequest request) {
        // TODO
    }
}

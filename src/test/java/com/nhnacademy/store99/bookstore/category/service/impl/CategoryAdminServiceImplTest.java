package com.nhnacademy.store99.bookstore.category.service.impl;

import com.nhnacademy.store99.bookstore.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.bookstore.category.entity.Category;
import com.nhnacademy.store99.bookstore.category.exception.CategoryNotFoundException;
import com.nhnacademy.store99.bookstore.category.repository.CategoryRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author seunggyu-kim
 */
@ExtendWith(MockitoExtension.class)
class CategoryAdminServiceImplTest {
    @InjectMocks
    private CategoryAdminServiceImpl categoryAdminService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void getCategories() {
        // TODO
    }

    @DisplayName("카테고리 추가 성공")
    @Test
    void addCategoryAndGetId() {
        // given
        long parentCategoryId = Mockito.anyLong();
        AddCategoryRequest request = new AddCategoryRequest("New Category", 2, parentCategoryId);

        Category parentCategory = Category.builder()
                .id(parentCategoryId)
                .categoryName("Parent Category")
                .categoryDepth(1)
                .build();

        Category category = Category.builder()
                .id(1L)
                .categoryName("New Category")
                .categoryDepth(2)
                .parentCategory(parentCategory)
                .build();

        BDDMockito.given(categoryRepository.findById(parentCategoryId)).willReturn(Optional.ofNullable(parentCategory));
        BDDMockito.given(categoryRepository.save(Mockito.any(Category.class))).willReturn(category);

        // when
        categoryAdminService.addCategoryAndGetId(request);

        // then
        Mockito.verify(categoryRepository, Mockito.times(1)).save(Mockito.any(Category.class));
    }

    @DisplayName("카테고리 추가 실패 - 없는 부모 카테고리")
    @Test
    void addCategoryAndGetIdWithNotFoundParent() {
        // given
        long parentCategoryId = Mockito.anyLong();
        AddCategoryRequest request = new AddCategoryRequest("New Category", 2, parentCategoryId);

        BDDMockito.given(categoryRepository.findById(parentCategoryId)).willReturn(Optional.empty());

        // when
        Assertions.assertThatThrownBy(() -> categoryAdminService.addCategoryAndGetId(request))
                .isInstanceOf(CategoryNotFoundException.class)
                .hasMessage("Category not found (categoryId: %d)", parentCategoryId);


        // then
        Mockito.verify(categoryRepository, Mockito.times(1)).findById(parentCategoryId);
        Mockito.verify(categoryRepository, Mockito.never()).save(Mockito.any(Category.class));
    }

    @Test
    void modifyCategory() {
        // TODO
    }

    @Test
    void removeCategory() {
        // TODO
    }
}
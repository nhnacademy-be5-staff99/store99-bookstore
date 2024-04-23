package com.nhnacademy.store99.bookstore.category.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nhnacademy.store99.bookstore.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.bookstore.category.entity.Category;
import com.nhnacademy.store99.bookstore.category.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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
        // given
        PageRequest pageable = PageRequest.of(0, 10);
        List<Category> cateogryList = List.of(
                Category.builder().id(1L).categoryName("국내도서").categoryDepth(1).build(),
                Category.builder().id(2L).categoryName("과학").categoryDepth(2)
                        .parentCategory(Category.builder().id(1L).categoryName("국내도서").categoryDepth(1).build())
                        .build(),
                Category.builder().id(3L).categoryName("컴퓨터/모바일").categoryDepth(2)
                        .parentCategory(Category.builder().id(1L).categoryName("국내도서").categoryDepth(1).build())
                        .build(),
                Category.builder().id(4L).categoryName("경제경영").categoryDepth(2)
                        .parentCategory(Category.builder().id(1L).categoryName("국내도서").categoryDepth(1).build())
                        .build(),
                Category.builder().id(5L).categoryName("대학교재/전문서적").categoryDepth(2)
                        .parentCategory(Category.builder().id(1L).categoryName("국내도서").categoryDepth(1).build())
                        .build(),
                Category.builder().id(6L).categoryName("프로그래밍 개발/방법론").categoryDepth(3)
                        .parentCategory(Category.builder().id(3L).categoryName("컴퓨터/모바일").categoryDepth(2).build())
                        .build(),
                Category.builder().id(7L).categoryName("인공지능").categoryDepth(3)
                        .parentCategory(Category.builder().id(3L).categoryName("컴퓨터/모바일").categoryDepth(2).build())
                        .build(),
                Category.builder().id(8L).categoryName("컴퓨터 공학").categoryDepth(3)
                        .parentCategory(Category.builder().id(3L).categoryName("컴퓨터/모바일").categoryDepth(2).build())
                        .build(),
                Category.builder().id(9L).categoryName("프로그래밍 언어").categoryDepth(3)
                        .parentCategory(Category.builder().id(3L).categoryName("컴퓨터/모바일").categoryDepth(2).build())
                        .build(),
                Category.builder().id(10L).categoryName("기초과학/교양과학").categoryDepth(3)
                        .parentCategory(Category.builder().id(2L).categoryName("과학").categoryDepth(2).build()).build()
        );
        Page<Category> categoryPage = new PageImpl<>(cateogryList);

        List<CategoryForAdminResponse> categoCategoryForAdminResponseList = List.of(
                CategoryForAdminResponse.builder().id(1L).categoryName("국내도서").categoryDepth(1).build(),
                CategoryForAdminResponse.builder().id(2L).categoryName("과학").categoryDepth(2).parentCategoryId(1L)
                        .build(),
                CategoryForAdminResponse.builder().id(3L).categoryName("컴퓨터/모바일").categoryDepth(2).parentCategoryId(1L)
                        .build(),
                CategoryForAdminResponse.builder().id(4L).categoryName("경제경영").categoryDepth(2).parentCategoryId(1L)
                        .build(),
                CategoryForAdminResponse.builder().id(5L).categoryName("대학교재/전문서적").categoryDepth(2)
                        .parentCategoryId(1L).build(),
                CategoryForAdminResponse.builder().id(6L).categoryName("프로그래밍 개발/방법론").categoryDepth(3)
                        .parentCategoryId(3L).build(),
                CategoryForAdminResponse.builder().id(7L).categoryName("인공지능").categoryDepth(3).parentCategoryId(3L)
                        .build(),
                CategoryForAdminResponse.builder().id(8L).categoryName("컴퓨터 공학").categoryDepth(3).parentCategoryId(3L)
                        .build(),
                CategoryForAdminResponse.builder().id(9L).categoryName("프로그래밍 언어").categoryDepth(3).parentCategoryId(3L)
                        .build(),
                CategoryForAdminResponse.builder().id(10L).categoryName("기초과학/교양과학").categoryDepth(3)
                        .parentCategoryId(2L).build()
        );
        Page<CategoryForAdminResponse> expectedCategoryPage = new PageImpl<>(categoCategoryForAdminResponseList);

        given(categoryRepository.findAll(pageable)).willReturn(categoryPage);

        // when
        Page<CategoryForAdminResponse> actualCategoryPage = categoryAdminService.getCategories(pageable);

        // then
        Assertions.assertThat(actualCategoryPage).usingRecursiveComparison().isEqualTo(expectedCategoryPage);
    }

    @DisplayName("카테고리 추가 성공 - 부모 없음")
    @Test
    void addCategoryAndGetIdWithNoParent() {
        // given
        AddCategoryRequest request = new AddCategoryRequest("New Category", null);

        Category category = Category.builder()
                .id(1L)
                .categoryName("New Category")
                .categoryDepth(1)
                .parentCategory(null)
                .build();

        given(categoryRepository.save(any(Category.class))).willReturn(category);

        // when
        Long actual = categoryAdminService.addCategoryAndGetId(request);

        // then
        Assertions.assertThat(actual).isEqualTo(category.getId());
    }

    @DisplayName("카테고리 추가 성공 - 부모 있음")
    @Test
    void addCategoryAndGetIdWithParent() {
        // given
        AddCategoryRequest request = new AddCategoryRequest("New Category", 1L);

        Category parentCategory = Category.builder()
                .id(1L)
                .categoryName("Parent Category")
                .categoryDepth(1)
                .build();

        Category category = Category.builder()
                .id(10L)
                .categoryName(request.getCategoryName())
                .categoryDepth(parentCategory.getCategoryDepth() + 1)
                .parentCategory(parentCategory)
                .build();

        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(parentCategory));
        given(categoryRepository.save(any(Category.class))).willReturn(category);

        // when
        Long actual = categoryAdminService.addCategoryAndGetId(request);

        // then
        Assertions.assertThat(actual).isEqualTo(category.getId());
    }

    @DisplayName("카테고리 추가 성공 - 없는 부모 카테고리")
    @Test
    void addCategoryAndGetIdWithNotFoundParent() {
        // given
        AddCategoryRequest request = new AddCategoryRequest("New Category", 1L);

        Category category = Category.builder()
                .id(10L)
                .categoryName(request.getCategoryName())
                .categoryDepth(1)
                .parentCategory(null)
                .build();

        given(categoryRepository.findById(anyLong())).willReturn(Optional.empty());
        given(categoryRepository.save(any(Category.class))).willReturn(category);

        // when
        Long actual = categoryAdminService.addCategoryAndGetId(request);

        // then
        Assertions.assertThat(actual).isEqualTo(category.getId());
    }

    @DisplayName("카테고리 수정 성공")
    @Test
    void modifyCategorySuccessfully() {
        // given
        ModifyCategoryRequest request = new ModifyCategoryRequest("Modified Category", 1L);

        Category parentCategory = Category.builder()
                .id(1L)
                .categoryName("Parent Category")
                .categoryDepth(1)
                .build();

        Category category = Category.builder()
                .id(10L)
                .categoryName(request.getCategoryName())
                .categoryDepth(parentCategory.getCategoryDepth() + 1)
                .parentCategory(parentCategory)
                .build();

        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(category));

        // when
        categoryAdminService.modifyCategory(category.getId(), request);

        // then
        verify(categoryRepository, times(2)).findById(anyLong());
    }

    @DisplayName("카테고리 삭제 성공")
    @Test
    void removeCategorySuccessfully() {
        // given
        Category category = Category.builder()
                .id(1L)
                .categoryName("Category to be deleted")
                .categoryDepth(1)
                .build();

        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(category));

        // when
        categoryAdminService.removeCategory(category.getId());

        // then
        verify(categoryRepository, times(1)).findById(anyLong());
    }

    @DisplayName("카테고리 삭제 취소 성공")
    @Test
    void restoreCategorySuccessfully() {
        // given
        Category category = Category.builder()
                .id(1L)
                .categoryName("Category to be undeleted")
                .categoryDepth(1)
                .build();

        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(category));

        // when
        categoryAdminService.restoreCategory(category.getId());

        // then
        verify(categoryRepository, times(1)).findById(anyLong());
    }
}
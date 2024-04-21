package com.nhnacademy.store99.bookstore.category.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.nhnacademy.store99.bookstore.category.dto.ActiveCategoryIdAndNameDto;
import com.nhnacademy.store99.bookstore.category.dto.response.CategoryChildrenListAndRouteResponse;
import com.nhnacademy.store99.bookstore.category.entity.Category;
import com.nhnacademy.store99.bookstore.category.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author seunggyu-kim
 */
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @DisplayName("카테고리 자식 목록과 경로 조회 - 성공")
    @Test
    void getChildrenListAndRoute_Success() {
        // given
        Long categoryId = 1L;
        Category category1 = Category.builder().id(categoryId).categoryName("국내도서").categoryDepth(1).build();
        Category category2 =
                Category.builder().id(2L).categoryName("과학").categoryDepth(2).parentCategory(category1).build();

        List<ActiveCategoryIdAndNameDto> childrenCategories = List.of(
                new ActiveCategoryIdAndNameDto(3L, "컴퓨터/모바일"),
                new ActiveCategoryIdAndNameDto(4L, "인문/사회")
        );

        given(categoryRepository.findById(categoryId)).willReturn(Optional.of(category2));
        given(categoryRepository.getCategoriesByParentCategory_IdAndDeletedAtIsNull(categoryId)).willReturn(
                childrenCategories);

        // when
        CategoryChildrenListAndRouteResponse result = categoryService.getChildrenListAndRoute(categoryId);

        // then
        List<ActiveCategoryIdAndNameDto> expectedRoute =
                List.of(new ActiveCategoryIdAndNameDto(2L, "과학"), new ActiveCategoryIdAndNameDto(1L, "국내도서"));

        SoftAssertions.assertSoftly(softly -> {
            assertThat(result.getNowCategoryRoute()).usingRecursiveComparison().isEqualTo(expectedRoute);
            assertThat(result.getChildrenCategoryList()).usingRecursiveComparison().isEqualTo(childrenCategories);
        });
    }

    @DisplayName("카테고리 자식 목록과 경로 조회 - 카테고리 없음")
    @Test
    void getChildrenListAndRoute_NoCategory() {
        // given
        Long categoryId = 1L;

        given(categoryRepository.findById(categoryId)).willReturn(Optional.empty());

        // when
        CategoryChildrenListAndRouteResponse result = categoryService.getChildrenListAndRoute(categoryId);

        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(result.getNowCategoryRoute()).isNull();
            assertThat(result.getChildrenCategoryList()).isEmpty();
        });
    }
}
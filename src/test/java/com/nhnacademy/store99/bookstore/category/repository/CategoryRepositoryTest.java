package com.nhnacademy.store99.bookstore.category.repository;

import com.nhnacademy.store99.bookstore.category.dto.ActiveCategoryIdAndNameDto;
import com.nhnacademy.store99.bookstore.category.entity.Category;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author seunggyu-kim
 */
@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @DisplayName("카테고리 저장 - 부모 카테고리 없음")
    @Test
    void saveWithNoParent() {
        // given
        Category category = Category.builder()
                .categoryName("카테고리")
                .categoryDepth(1)
                .parentCategory(null)
                .build();

        // when
        Category actualCategory = categoryRepository.save(category);

        // then
        Category expectedCategory = Category.builder()
                .id(actualCategory.getId())
                .categoryName("카테고리")
                .categoryDepth(1)
                .parentCategory(null)
                .build();

        Assertions.assertThat(actualCategory).isEqualTo(expectedCategory);
    }

    @DisplayName("카테고리 저장 - 없는 부모 카테고리 지정")
    @Test
    void saveWithNotExistParent() {
        // given
        Category parentCategory = Category.builder().id(10L).build();
        Category category = Category.builder()
                .categoryName("카테고리")
                .categoryDepth(2)
                .parentCategory(parentCategory)
                .build();

        // when & then
        Assertions.assertThatThrownBy(() -> categoryRepository.save(category)).isInstanceOf(
                DataIntegrityViolationException.class).hasMessageContaining("");
    }

    @DisplayName("카테고리 저장 - 부모 카테고리 있음")
    @Test
    void saveWithParent() {
        // given
        Category category1 = Category.builder()
                .categoryName("카테고리1")
                .categoryDepth(1)
                .parentCategory(null)
                .build();

        categoryRepository.save(category1);

        Category category2 = Category.builder()
                .categoryName("카테고리2")
                .categoryDepth(2)
                .parentCategory(category1)
                .build();

        // when
        Category actualCategory = categoryRepository.save(category2);

        // then
        Category expectedCategory = Category.builder()
                .id(actualCategory.getId())
                .categoryName("카테고리2")
                .categoryDepth(2)
                .parentCategory(category1)
                .build();

        Assertions.assertThat(actualCategory).isEqualTo(expectedCategory);
    }

    @DisplayName("부모 카테고리 ID로 사용가능한 카테고리 목록 조회")
    @Test
    void getCategoriesByParentCategoryIdAndNotDeleted() {
        // given
        Category parentCategory = Category.builder()
                .categoryName("Parent Category")
                .categoryDepth(1)
                .parentCategory(null)
                .build();

        categoryRepository.save(parentCategory);

        Category childCategory = Category.builder()
                .categoryName("Child Category")
                .categoryDepth(2)
                .parentCategory(parentCategory)
                .build();

        categoryRepository.save(childCategory);

        // when
        List<ActiveCategoryIdAndNameDto> actualCategories =
                categoryRepository.getCategoriesByParentCategory_IdAndDeletedAtIsNull(parentCategory.getId());

        // then
        List<ActiveCategoryIdAndNameDto> expectedCategories = List.of(
                new ActiveCategoryIdAndNameDto(childCategory.getId(), childCategory.getCategoryName())
        );

        Assertions.assertThat(actualCategories).hasSize(1);
        Assertions.assertThat(actualCategories).usingRecursiveComparison().isEqualTo(expectedCategories);
    }

    @DisplayName("존재하지 않는 부모 카테고리 ID로 카테고리 조회")
    @Test
    void getCategoriesByNonExistentParentCategoryId() {
        // when
        List<ActiveCategoryIdAndNameDto> actualCategories =
                categoryRepository.getCategoriesByParentCategory_IdAndDeletedAtIsNull(999L);

        // then
        Assertions.assertThat(actualCategories).isEmpty();
    }
}
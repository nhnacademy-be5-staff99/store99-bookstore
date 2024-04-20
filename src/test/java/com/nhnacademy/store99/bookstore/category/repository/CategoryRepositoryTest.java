package com.nhnacademy.store99.bookstore.category.repository;

import com.nhnacademy.store99.bookstore.category.entity.Category;
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
}
package com.nhnacademy.store99.bookstore.category.repository;

import com.nhnacademy.store99.bookstore.category.dto.ActiveCategoryIdAndNameDto;
import com.nhnacademy.store99.bookstore.category.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author seunggyu-kim
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<ActiveCategoryIdAndNameDto> getCategoriesByParentCategory_IdAndDeletedAtIsNull(final Long parentCategory_id);
}

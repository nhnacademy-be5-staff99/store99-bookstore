package com.nhnacademy.store99.bookstore.category.repository;

import com.nhnacademy.store99.bookstore.category.dto.response.ActiveCategoryResponse;
import com.nhnacademy.store99.bookstore.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.bookstore.category.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    CategoryForAdminResponse queryById(Long id);

    List<ActiveCategoryResponse> getCategoriesByCategoryDepth(Integer depth);
}

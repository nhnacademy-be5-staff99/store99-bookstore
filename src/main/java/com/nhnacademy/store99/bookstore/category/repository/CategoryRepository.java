package com.nhnacademy.store99.bookstore.category.repository;

import com.nhnacademy.store99.bookstore.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

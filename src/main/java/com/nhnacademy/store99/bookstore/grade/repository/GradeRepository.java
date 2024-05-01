package com.nhnacademy.store99.bookstore.grade.repository;

import com.nhnacademy.store99.bookstore.grade.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}

package com.nhnacademy.store99.bookstore.tag.repository;

import com.nhnacademy.store99.bookstore.tag.dto.response.TagResponse;
import com.nhnacademy.store99.bookstore.tag.entity.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.JpaQueryExecution;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Boolean existsTagByTagName(String tagName);
}

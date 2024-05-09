package com.nhnacademy.store99.bookstore.tag.repository;

import com.nhnacademy.store99.bookstore.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Boolean existsTagByTagName(String tagName);
}

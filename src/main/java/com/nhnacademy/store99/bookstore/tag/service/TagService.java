package com.nhnacademy.store99.bookstore.tag.service;


import com.nhnacademy.store99.bookstore.tag.dto.request.CreateTagRequest;
import com.nhnacademy.store99.bookstore.tag.dto.request.ModifyTagRequest;
import com.nhnacademy.store99.bookstore.tag.dto.response.TagResponse;
import java.util.List;

/**
 * 태그 서비스 인터페이스
 *
 * @author rosin23
 */

public interface TagService {
    TagResponse createTag(CreateTagRequest request);

    List<TagResponse> findAllTags();

    Boolean existsByTagName(String tagName);

    TagResponse updateTag(Long id, ModifyTagRequest request);

    void deleteTag(Long id);
}

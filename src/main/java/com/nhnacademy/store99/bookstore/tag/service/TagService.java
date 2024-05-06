package com.nhnacademy.store99.bookstore.tag.service;


import com.nhnacademy.store99.bookstore.book_tag.entity.BookTag;
import com.nhnacademy.store99.bookstore.tag.dto.request.CreateTagRequest;
import com.nhnacademy.store99.bookstore.tag.dto.request.ModifyTagRequest;
import com.nhnacademy.store99.bookstore.tag.dto.response.TagResponse;
import java.util.List;

public interface TagService  {
    TagResponse createTag(CreateTagRequest request);
    List<TagResponse> findAllTags();
//    TagResponse findTagById(Long id);
    Boolean existsByTagName(String tagName);
    TagResponse updateTag(Long id, ModifyTagRequest request);
    void detleteTag(Long id);
}

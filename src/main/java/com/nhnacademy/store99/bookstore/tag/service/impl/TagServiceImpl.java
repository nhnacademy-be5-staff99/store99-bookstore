package com.nhnacademy.store99.bookstore.tag.service.impl;

import com.nhnacademy.store99.bookstore.common.exception.AlreadyExistsException;
import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;
import com.nhnacademy.store99.bookstore.tag.dto.request.CreateTagRequest;
import com.nhnacademy.store99.bookstore.tag.dto.request.ModifyTagRequest;
import com.nhnacademy.store99.bookstore.tag.dto.response.TagResponse;
import com.nhnacademy.store99.bookstore.tag.entity.Tag;
import com.nhnacademy.store99.bookstore.tag.repository.TagRepository;
import com.nhnacademy.store99.bookstore.tag.service.TagService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 태그 관리자 서비스 구현
 *
 * @author  rosin23
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Transactional
    @Override
    public TagResponse createTag(CreateTagRequest request) throws AlreadyExistsException {
        if(Boolean.TRUE.equals(existsByTagName(request.getTagName()))) {
            throw new AlreadyExistsException("이미 존재하는 태그명입니다");
        }

        Tag tag = tagRepository.save(Tag.builder()
                                        .tagName(request.getTagName())
                                        .build());
        return new TagResponse(tag);
    }

    @Override
    public List<TagResponse> findAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(TagResponse::new).collect(Collectors.toList());
    }
/*x
    @Override
    public TagResponse findTagById(Long id) {

        Tag tag = tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException("해당 id의 tag를 찾을 수 없습니다"));
        return new TagResponse(tag);
    }
*/
    @Override
    public Boolean existsByTagName(String tagName) {
        return tagRepository.existsTagByTagName(tagName);
    }

    @Transactional
    @Override
    public TagResponse updateTag(Long id, ModifyTagRequest request) {
        if (Boolean.TRUE.equals(tagRepository.existsTagByTagName(request.getTagName()))) {
            throw new  AlreadyExistsException("이미 존재하는 태그명입니다");
        }

        Tag tag = tagRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("해당 태그의 Id 존재하지 않음: " + request.getId()));

        tag.setTagName(request.getTagName());

        return new TagResponse(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

}
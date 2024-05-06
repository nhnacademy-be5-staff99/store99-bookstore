package com.nhnacademy.store99.bookstore.tag.service.impl;

import com.nhnacademy.store99.bookstore.tag.dto.request.CreateTagRequest;
import com.nhnacademy.store99.bookstore.tag.dto.request.ModifyTagRequest;
import com.nhnacademy.store99.bookstore.tag.dto.response.TagResponse;
import com.nhnacademy.store99.bookstore.tag.entity.Tag;
import com.nhnacademy.store99.bookstore.tag.exception.TagAlreadyInUseException;
import com.nhnacademy.store99.bookstore.tag.exception.TagNotFoundException;
import com.nhnacademy.store99.bookstore.tag.repository.TagRepository;
import com.nhnacademy.store99.bookstore.tag.service.TagService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Transactional
    @Override
    public TagResponse createTag(CreateTagRequest request) throws TagAlreadyInUseException {
        if(Boolean.TRUE.equals(existsByTagName(request.getTagName()))) {
            throw new TagAlreadyInUseException(HttpStatus.CONFLICT, "이미 존재하는 태그 입니다");
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
            throw new TagAlreadyInUseException(HttpStatus.CONFLICT, "이미 존재하는 Tag입니다");
        }

        Tag tag = tagRepository.findById(request.getId())
                .orElseThrow(() -> new TagNotFoundException("해당 id의 태그 없음: " + request.getId()));

        tag.setTagName(request.getTagName());

        return new TagResponse(tag);
    }

    @Transactional
    @Override
    public void detleteTag(Long id) {
        tagRepository.deleteById(id);
    }

}
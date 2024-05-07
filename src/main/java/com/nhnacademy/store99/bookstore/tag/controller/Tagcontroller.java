package com.nhnacademy.store99.bookstore.tag.controller;

import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.tag.dto.request.CreateTagRequest;
import com.nhnacademy.store99.bookstore.tag.dto.request.ModifyTagRequest;
import com.nhnacademy.store99.bookstore.tag.dto.response.TagResponse;
import com.nhnacademy.store99.bookstore.tag.service.TagService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 태그 REST 컨트롤러
 *
 * @author  rosin23
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/tags")
public class Tagcontroller {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<CommonResponse<TagResponse>> createTag(@Valid @RequestBody CreateTagRequest request) {
            TagResponse res = tagService.createTag(request);
            CommonHeader header = CommonHeader.builder()
                                            .httpStatus(HttpStatus.CREATED)
                                            .resultMessage("tag 생성 완료")
                                            .build();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CommonResponse.<TagResponse>builder()
                                            .header(header)
                                            .result(res)
                                            .build());
    }

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTag() {
        return ResponseEntity.ok(tagService.findAllTags());
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getTagById(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.findTagById(id));
    }
*/
    @PutMapping("/{id}")
    public void updateTag(@PathVariable Long id, @Valid @RequestBody ModifyTagRequest request) {
        tagService.updateTag(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable @Valid Long id) {
        tagService.deleteTag(id);
    }
}

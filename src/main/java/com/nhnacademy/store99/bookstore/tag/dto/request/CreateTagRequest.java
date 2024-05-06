package com.nhnacademy.store99.bookstore.tag.dto.request;

import com.nhnacademy.store99.bookstore.tag.entity.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 태그 생성요청 DTO
 *
 * @Author rosin23
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateTagRequest {

    private String tagName;
}

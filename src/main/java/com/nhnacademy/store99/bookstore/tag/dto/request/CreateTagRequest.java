package com.nhnacademy.store99.bookstore.tag.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 태그 생성요청 DTO
 *
 * @author rosin23
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateTagRequest {

    @NotBlank
    @Size(min = 1, max = 255)
    private String tagName;
}

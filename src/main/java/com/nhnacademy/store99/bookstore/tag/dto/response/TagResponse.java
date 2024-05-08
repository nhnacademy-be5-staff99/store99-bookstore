package com.nhnacademy.store99.bookstore.tag.dto.response;

import com.nhnacademy.store99.bookstore.tag.entity.Tag;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 태그 응답 DTO
 *
 * @author  rosin23
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagResponse {

    @NotBlank
    private Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    private String tagName;

    private LocalDateTime deletedAt;

    public TagResponse(Tag tag) {
        this.id = tag.getId();
        this.tagName = tag.getTagName();
    }
}

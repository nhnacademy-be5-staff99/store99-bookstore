package com.nhnacademy.store99.bookstore.tag.dto.response;

import com.nhnacademy.store99.bookstore.tag.entity.Tag;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagResponse {

    private Long id;
    private String tagName;
    private LocalDateTime deletedAt;



    public TagResponse(Tag tag) {
        this.id = tag.getId();
        this.tagName = tag.getTagName();
    }
}

package com.nhnacademy.store99.bookstore.tag.dto.request;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModifyTagRequest {

    private Long id;
    private String tagName;
    private LocalDateTime deletedAt;

}

package com.nhnacademy.store99.bookstore.tag.dto.request;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 태그 수정요청 DTO
 *
 * @author rosin23
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModifyTagRequest {

    @NotNull
    private Long id;

    @Size(min = 1, max = 255)
    @NotBlank
    private String tagName;

    private LocalDateTime deletedAt;

}

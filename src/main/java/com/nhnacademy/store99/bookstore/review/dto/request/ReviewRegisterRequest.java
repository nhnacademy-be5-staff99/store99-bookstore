package com.nhnacademy.store99.bookstore.review.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewRegisterRequest {

    @Size(min = 1, max = 3000)
    @Max(value = 3000, message = "리뷰 작성 가능한 텍스트가 초과하였습니다.")
    @Min(value = 1, message = "텍스트를 작성해주세요.")
    private String reviewDescription;

    @NotNull
    private Integer reviewRate;

    @NotBlank
    private Long bookId;

}

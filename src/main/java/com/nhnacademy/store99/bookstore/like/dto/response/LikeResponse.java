package com.nhnacademy.store99.bookstore.like.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LikeResponse {

    private Long bookId;

    private Long userId;
}

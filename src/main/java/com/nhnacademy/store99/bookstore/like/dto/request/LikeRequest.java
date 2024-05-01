package com.nhnacademy.store99.bookstore.like.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeRequest {

    private Long bookId;

    private Long userId;
}


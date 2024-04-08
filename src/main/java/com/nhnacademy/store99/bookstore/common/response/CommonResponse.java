package com.nhnacademy.store99.bookstore.common.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 단 건 공통 응답 객체
 *
 * @param <T>
 * @author seunggyu-kim
 */
@Getter
public class CommonResponse<T> {
    private final CommonHeader header;
    private final T result;

    @Builder
    private CommonResponse(CommonHeader header, T result) {
        this.header = header;
        this.result = result;
    }
}
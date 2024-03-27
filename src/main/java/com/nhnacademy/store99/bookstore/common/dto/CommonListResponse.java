package com.nhnacademy.store99.bookstore.common.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonListResponse<T> {
    private final CommonHeader header;
    private final List<T> resultList;

    @Builder
    private CommonListResponse(CommonHeader header, List<T> resultList) {
        this.header = header;
        this.resultList = resultList;
    }
}
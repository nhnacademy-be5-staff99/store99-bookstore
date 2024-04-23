package com.nhnacademy.store99.bookstore.search.dto.request;

import lombok.Getter;

@Getter
public class BasicSearchRequest {
    private final String searchContent;

    public BasicSearchRequest(String searchContent) {
        this.searchContent = searchContent;
    }
}

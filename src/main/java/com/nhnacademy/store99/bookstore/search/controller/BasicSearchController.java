package com.nhnacademy.store99.bookstore.search.controller;

import com.nhnacademy.store99.bookstore.search.dto.BasicSearchResponse;
import com.nhnacademy.store99.bookstore.search.service.SearchService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 도서 검색
 *
 * @author Ahyeon Song
 */
@RestController
@RequestMapping("/open/v1/search")
public class BasicSearchController {

    @Qualifier("basicSearchService")
    private final SearchService searchService;

    public BasicSearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * 도서 검색 시 검색 문자열을 파라미터로 받음
     *
     * <p>ex) /open/v1/search?content=자바
     */
    @GetMapping(params = "content")
    public Page<BasicSearchResponse> getSearchResult(@Valid @RequestParam String content, Pageable pageable) {
        return searchService.getSearchResult(content, pageable);
    }

}

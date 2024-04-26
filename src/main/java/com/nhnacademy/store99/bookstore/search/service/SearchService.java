package com.nhnacademy.store99.bookstore.search.service;


import com.nhnacademy.store99.bookstore.search.dto.BasicSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Ahyeon Song
 */
public interface SearchService {
    Page<BasicSearchResponse> getSearchResult(String content, Pageable pageable);
}

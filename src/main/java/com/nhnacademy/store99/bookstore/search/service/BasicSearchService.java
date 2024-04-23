package com.nhnacademy.store99.bookstore.search.service;

import com.nhnacademy.store99.bookstore.search.dto.BasicSearchResponse;
import com.nhnacademy.store99.bookstore.search.query.BasicSearchQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 도서 검색
 *
 * @author Ahyeon Song
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class BasicSearchService implements SearchService {

    @Qualifier("basicSearchQueryImpl")
    private final BasicSearchQuery basicSearchQuery;

    public BasicSearchService(BasicSearchQuery basicSearchQuery) {
        this.basicSearchQuery = basicSearchQuery;
    }

    /**
     * 도서 검색 결과를 Page 적용 하여 반환
     */
    public Page<BasicSearchResponse> getSearchResult(String content, Pageable pageable) {
        return basicSearchQuery.getSearchResult(content, pageable);
    }

}

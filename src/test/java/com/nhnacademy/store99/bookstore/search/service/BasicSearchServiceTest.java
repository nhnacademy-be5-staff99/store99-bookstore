package com.nhnacademy.store99.bookstore.search.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.nhnacademy.store99.bookstore.search.dto.BasicSearchResponse;
import com.nhnacademy.store99.bookstore.search.query.BasicSearchQuery;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class BasicSearchServiceTest {
    @InjectMocks
    private BasicSearchService searchService;

    @Mock
    private BasicSearchQuery basicSearchQuery;

    @DisplayName("단순 도서 조회 - 성공")
    @Test
    void getSearchResult() {
        // exist
        List<BasicSearchResponse> existSearchList = List.of(
                BasicSearchResponse.builder()
                        .bookTitle("AI 2024 - 트렌드 & 활용백과")
                        .bookThumbnailUrl("https://image.aladin.co.kr/product/32628/53/cover/k822935456_1.jpg")
                        .bookAuthorInfos(List.of(
                                BasicSearchResponse.BookAuthorInfo.builder()
                                        .authorName("김덕진")
                                        .authorType("AUTHOR")
                                        .build()))
                        .bookPublisher("스마트북스")
                        .bookDate(LocalDateTime.parse("2023-10-25T09:00:00"))
                        .bookPrice(22000)
                        .bookSalePrice(19800)
                        .bookCntOfReview(0)
                        .build(),
                BasicSearchResponse.builder()
                        .bookTitle("컴퓨터 밑바닥의 비밀 - 컴퓨터 시스템의 본질을 알면 코드의 실마리가 보인다")
                        .bookThumbnailUrl("https://image.aladin.co.kr/product/33564/48/cover/k602939885_1.jpg")
                        .bookAuthorInfos(List.of(
                                BasicSearchResponse.BookAuthorInfo.builder()
                                        .authorName("루 샤오펑")
                                        .authorType("AUTHOR")
                                        .build(),
                                BasicSearchResponse.BookAuthorInfo.builder()
                                        .authorName("김진호")
                                        .authorType("TRANSLATOR")
                                        .build()))
                        .bookPublisher("길벗")
                        .bookDate(LocalDateTime.parse("2024-03-11T09:00:00"))
                        .bookPrice(33000)
                        .bookSalePrice(29700)
                        .bookCntOfReview(0)
                        .build(),
                BasicSearchResponse.builder()
                        .bookTitle("김으로 시작하는 책")
                        .bookThumbnailUrl("null.jpg")
                        .bookAuthorInfos(List.of(
                                BasicSearchResponse.BookAuthorInfo.builder()
                                        .authorName("송아현")
                                        .authorType("AUTHOR")
                                        .build()))
                        .bookDate(LocalDateTime.parse("2024-04-25T09:00:00"))
                        .bookPrice(999990)
                        .bookSalePrice(99990)
                        .bookCntOfReview(9999)
                        .build(),
                BasicSearchResponse.builder()
                        .bookTitle("김으로 끝나는 책김책김")
                        .bookThumbnailUrl("null.jpg")
                        .bookAuthorInfos(List.of(
                                BasicSearchResponse.BookAuthorInfo.builder()
                                        .authorName("송아현")
                                        .authorType("AUTHOR")
                                        .build(),
                                BasicSearchResponse.BookAuthorInfo.builder()
                                        .authorName("김아현")
                                        .authorType("TRANSLATOR")
                                        .build()))
                        .bookDate(LocalDateTime.parse("2024-04-25T09:00:00"))
                        .bookPrice(999990)
                        .bookSalePrice(99990)
                        .bookCntOfReview(9999)
                        .build()
        );
        Page<BasicSearchResponse> exist = new PageImpl<>(existSearchList);

        // given
        List<BasicSearchResponse> givenSearchList = List.of(
                BasicSearchResponse.builder()
                        .bookTitle("AI 2024 - 트렌드 & 활용백과")
                        .bookThumbnailUrl("https://image.aladin.co.kr/product/32628/53/cover/k822935456_1.jpg")
                        .bookAuthorInfos(List.of(
                                BasicSearchResponse.BookAuthorInfo.builder()
                                        .authorName("김덕진")
                                        .authorType("AUTHOR")
                                        .build()))
                        .bookPublisher("스마트북스")
                        .bookDate(LocalDateTime.parse("2023-10-25T09:00:00"))
                        .bookPrice(22000)
                        .bookSalePrice(19800)
                        .bookCntOfReview(0)
                        .build(),
                BasicSearchResponse.builder()
                        .bookTitle("컴퓨터 밑바닥의 비밀 - 컴퓨터 시스템의 본질을 알면 코드의 실마리가 보인다")
                        .bookThumbnailUrl("https://image.aladin.co.kr/product/33564/48/cover/k602939885_1.jpg")
                        .bookAuthorInfos(List.of(
                                BasicSearchResponse.BookAuthorInfo.builder()
                                        .authorName("루 샤오펑")
                                        .authorType("AUTHOR")
                                        .build(),
                                BasicSearchResponse.BookAuthorInfo.builder()
                                        .authorName("김진호")
                                        .authorType("TRANSLATOR")
                                        .build()))
                        .bookPublisher("길벗")
                        .bookDate(LocalDateTime.parse("2024-03-11T09:00:00"))
                        .bookPrice(33000)
                        .bookSalePrice(29700)
                        .bookCntOfReview(0)
                        .build(),
                BasicSearchResponse.builder()
                        .bookTitle("김으로 시작하는 책")
                        .bookThumbnailUrl("null.jpg")
                        .bookAuthorInfos(List.of(
                                BasicSearchResponse.BookAuthorInfo.builder()
                                        .authorName("송아현")
                                        .authorType("AUTHOR")
                                        .build()))
                        .bookDate(LocalDateTime.parse("2024-04-25T09:00:00"))
                        .bookPrice(999990)
                        .bookSalePrice(99990)
                        .bookCntOfReview(9999)
                        .build(),
                BasicSearchResponse.builder()
                        .bookTitle("김으로 끝나는 책김책김")
                        .bookThumbnailUrl("null.jpg")
                        .bookAuthorInfos(List.of(
                                BasicSearchResponse.BookAuthorInfo.builder()
                                        .authorName("송아현")
                                        .authorType("AUTHOR")
                                        .build(),
                                BasicSearchResponse.BookAuthorInfo.builder()
                                        .authorName("김아현")
                                        .authorType("TRANSLATOR")
                                        .build()))
                        .bookDate(LocalDateTime.parse("2024-04-25T09:00:00"))
                        .bookPrice(999990)
                        .bookSalePrice(99990)
                        .bookCntOfReview(9999)
                        .build()
        );
        Page<BasicSearchResponse> given = new PageImpl<>(givenSearchList);
        given(basicSearchQuery.getSearchResult(any(String.class), any(Pageable.class))).willReturn(exist);
        PageRequest pageable = PageRequest.of(2, 2);

        // when
        Page<BasicSearchResponse> actualResult = searchService.getSearchResult("김", pageable);

        // then
        Assertions.assertThat(actualResult).usingRecursiveComparison().isEqualTo(given);
    }
}
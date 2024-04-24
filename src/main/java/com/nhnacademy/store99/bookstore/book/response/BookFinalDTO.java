package com.nhnacademy.store99.bookstore.book.response;

import java.time.LocalDateTime;

/**
 * <h1>도서</h1>
 * 도서 번호
 * isbn 13,10
 * 제목
 * 목차
 * 설명
 * 출판사
 * 출판일시
 * 정가
 * 판매가
 * 포장가능여부
 * 썸네일
 * 조회수
 * 재고
 * 리뷰 수
 * 평균평점
 * 생성일시
 * 수정일시
 * <h1>Other</h1>
 * 작가 이름
 * 작가 역할
 * 삭제일시 != null
 */
public interface BookFinalDTO {
    Long getBookId();

    String getBookBookIsbn13();

    String getBookBookIsbn10();

    String getBookBookTitle();

    String getBookBookContents();

    String getBookBookPublisher();

    LocalDateTime getBookBookDate();

    Integer getBookBookPrice();

    Integer getBookBookSalePrice();

    Boolean getBookBookIsPacked();

    String getBookBookThumbnailUrl();

    Integer getBookBookStock();

    Integer getBookBookCntOfReview();

    Double getBookBookAvgOfRate();

    LocalDateTime getBookCreatedAt();

    LocalDateTime getBookUpdatedAt();

    String getAuthorAuthorName();

    String getAuthorAuthorType();
}

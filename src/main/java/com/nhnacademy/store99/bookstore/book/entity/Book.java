package com.nhnacademy.store99.bookstore.book.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 도서 엔티티
 *
 * @author 김승규
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "books", schema = "staff99_bookstore")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Long id;

    @Size(max = 13)
    @Column(name = "book_isbn13", length = 13)
    private String bookIsbn13;

    @Size(max = 10)
    @Column(name = "book_isbn10", length = 10)
    private String bookIsbn10;

    @Size(max = 255)
    @NotNull
    @Column(name = "book_title", nullable = false)
    private String bookTitle;

    @Size(max = 255)
    @NotNull
    @Column(name = "book_contents", nullable = false)
    private String bookContents;

    @NotNull
    @Lob
    @Column(name = "book_description", nullable = false)
    private String bookDescription;

    @Size(max = 255)
    @NotNull
    @Column(name = "book_publisher", nullable = false)
    private String bookPublisher;

    @NotNull
    @Column(name = "book_date", nullable = false)
    private LocalDateTime bookDate;

    @NotNull
    @Column(name = "book_price", nullable = false)
    private Integer bookPrice;

    @NotNull
    @Column(name = "book_sale_price", nullable = false)
    private Integer bookSalePrice;

    @NotNull
    @Column(name = "book_is_packed", nullable = false)
    private Boolean bookIsPacked = false;

    @Size(max = 255)
    @Column(name = "book_thumbnail_url")
    private String bookThumbnailUrl;

    @Builder.Default
    @NotNull
    @Column(name = "book_view_count", nullable = false)
    private Integer bookViewCount = 0;

    @Builder.Default
    @NotNull
    @Column(name = "book_stock", nullable = false)
    private Integer bookStock = 0;

    @Builder.Default
    @NotNull
    @Column(name = "book_cnt_of_review", nullable = false)
    private Integer bookCntOfReview = 0;

    @Builder.Default
    @NotNull
    @Column(name = "book_avg_of_rate", nullable = false)
    private Double bookAvgOfRate = 0.0;

    @Builder.Default
    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
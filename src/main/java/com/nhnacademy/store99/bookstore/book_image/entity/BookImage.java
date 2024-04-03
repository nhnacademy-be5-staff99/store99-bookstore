package com.nhnacademy.store99.bookstore.book_image.entity;

import com.nhnacademy.store99.bookstore.book.entity.Book;
import com.nhnacademy.store99.bookstore.file.entity.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "book_images", schema = "staff99_bookstore")
public class BookImage {
    @Id
    @Column(name = "file_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "file_id", nullable = false)
    private File files;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Size(max = 10)
    @NotNull
    @Column(name = "book_image_type", nullable = false, length = 10)
    private String bookImageType;
}
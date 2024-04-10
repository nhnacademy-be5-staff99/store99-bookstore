package com.nhnacademy.store99.bookstore.wrapper_image.entity;

import com.nhnacademy.store99.bookstore.file.entity.File;
import com.nhnacademy.store99.bookstore.wrapper.entity.Wrapper;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * WrapperImage Entity
 *
 * @author seunggyu-kim
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "wrapper_images", schema = "staff99_bookstore")
public class WrapperImage {
    @Id
    @Column(name = "file_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "file_id", nullable = false)
    private File files;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wrapper_id", nullable = false)
    private Wrapper wrapper;

}
package com.nhnacademy.store99.bookstore.tag.entity;

import com.nhnacademy.store99.bookstore.book_tag.entity.BookTag;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Tag Entity
 *
 * @author seunggyu-kim
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tags", schema = "staff99_bookstore")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", nullable = false)
    private Long id;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    //@OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<BookTag> bookTags = new HashSet<>();


}
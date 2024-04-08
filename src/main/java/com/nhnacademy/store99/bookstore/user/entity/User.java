package com.nhnacademy.store99.bookstore.user.entity;

import com.nhnacademy.store99.bookstore.auth.entity.Auth;
import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import com.nhnacademy.store99.bookstore.grade.entity.Grade;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "users", schema = "staff99_bookstore")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_birthdate", nullable = false)
    private LocalDate userBirthdate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;

    @Column(name = "user_login_at")
    private LocalDateTime userLoginAt;

    @Builder.Default
    @Column(name = "user_is_inactive", nullable = false)
    private Boolean userIsInactive = false;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "auth_id", nullable = false)
    private Auth auth;

    @Builder.Default
    @Column(name = "user_point", nullable = false)
    private Integer userPoint = 0;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Consumer consumers;
}
package com.nhnacademy.store99.bookstore.point_history.entity;

import com.nhnacademy.store99.bookstore.point_history.enums.PointHistoryType;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "point_histories", schema = "staff99_bookstore")
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_history_id", nullable = false)
    private Long id;

    @Column(name = "point_history_value", nullable = false)
    private Integer pointHistoryValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "point_history_type", nullable = false, length = 20)
    private PointHistoryType pointHistoryType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "user_id", nullable = false)
    private Long userId;

}
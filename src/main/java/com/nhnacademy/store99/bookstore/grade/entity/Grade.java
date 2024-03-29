package com.nhnacademy.store99.bookstore.grade.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "grades", schema = "staff99_bookstore")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "grade_name", nullable = false, length = 10)
    private String gradeName;           // BASIC, ROYAL, GOLD, PLATINUM

    @NotNull
    @Column(name = "grade_start_cost", nullable = false)
    private Integer gradeStartCost;     // 시작 금액(이상)

    @NotNull
    @Column(name = "grade_end_cost", nullable = false)
    private Integer gradeEndCost;       // 끝 금액(미만)

    @NotNull
    @Column(name = "grade_ratio", nullable = false)
    private Integer gradeRatio;         // 접립률, 순수금액 * 적립률% 포인트 적립

}
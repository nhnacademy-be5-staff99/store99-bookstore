package com.nhnacademy.store99.bookstore.wrapper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "wrappers", schema = "staff99_bookstore")
public class Wrapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wrapper_id", nullable = false)
    private Long id;

    @Column(name = "wrapper_name", nullable = false)
    private String wrapperName;

    @Column(name = "wrapper_cost", nullable = false)
    private Integer wrapperCost;

}
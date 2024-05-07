package com.nhnacademy.store99.bookstore.point_history.repository.impl;

import com.nhnacademy.store99.bookstore.point_history.entity.PointHistory;
import com.nhnacademy.store99.bookstore.point_history.repository.PointRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class PointRepositoryImpl extends QuerydslRepositorySupport implements PointRepositoryCustom {
    public PointRepositoryImpl() {
        super(PointHistory.class);
    }
}

package com.nhnacademy.store99.bookstore.point_history.exception;

import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;

public class PointHistoryNotFoundException extends NotFoundException {
    public PointHistoryNotFoundException(String userId) {
        super(String.format("user id 로 포인트 내역을 조회할 수 없습니다. (userId : %s)", userId));
    }
}

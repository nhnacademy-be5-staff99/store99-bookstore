package com.nhnacademy.store99.bookstore.point_history.dto;

import com.nhnacademy.store99.bookstore.point_history.enums.PointHistoryType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * userId 에 해당하는 포인트 내역 반환 형식
 */
@Getter
@Builder
@AllArgsConstructor
public class UserPointResponse {
    private Integer pointHistoryValue;
    private PointHistoryType pointHistoryType;
    private LocalDateTime createdAt;
}

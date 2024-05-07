package com.nhnacademy.store99.bookstore.point_history.repository;

import com.nhnacademy.store99.bookstore.point_history.entity.PointHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointHistory, Long> {
    List<PointHistory> findAllByUserId(Long userId);
}

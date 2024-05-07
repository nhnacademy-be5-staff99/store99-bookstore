package com.nhnacademy.store99.bookstore.point_history.service;

import com.nhnacademy.store99.bookstore.point_history.dto.UserPointResponse;
import com.nhnacademy.store99.bookstore.point_history.repository.PointRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    /**
     * userId 에 해당하는 포인트 내역 반환
     * @param xUserId
     * @return UserPointResponse
     */
    public List<UserPointResponse> getUserPointHistories(Long xUserId){
        return null;
    }
}

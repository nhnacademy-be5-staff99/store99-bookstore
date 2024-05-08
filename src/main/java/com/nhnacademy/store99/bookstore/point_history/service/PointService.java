package com.nhnacademy.store99.bookstore.point_history.service;

import com.nhnacademy.store99.bookstore.common.thread_local.XUserIdThreadLocal;
import com.nhnacademy.store99.bookstore.point_history.dto.UserPointResponse;
import com.nhnacademy.store99.bookstore.point_history.entity.PointHistory;
import com.nhnacademy.store99.bookstore.point_history.repository.PointRepository;
import com.nhnacademy.store99.bookstore.user.entity.User;
import com.nhnacademy.store99.bookstore.user.exception.UserNotFoundException;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ahyeon Song
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    /**
     * userId 에 해당하는 포인트 내역 반환
     *
     * @return UserPointResponse
     */
    public List<UserPointResponse> getUserPointHistories() {
        Long xUserId = XUserIdThreadLocal.getXUserId();
        Optional<User> findUser = userRepository.findByIdAndUserIsInactiveFalse(xUserId);

        if (findUser.isEmpty()) {
            throw new UserNotFoundException(xUserId);
        }

        List<PointHistory> pointHistories =
                pointRepository.findAllByUserId(xUserId, Sort.by(Sort.Direction.DESC, "createdAt"));

        return pointHistories
                .stream()
                .map(UserPointResponse::new)
                .collect(Collectors.toList());
    }
}

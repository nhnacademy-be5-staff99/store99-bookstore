package com.nhnacademy.store99.bookstore.auth.service.impl;

import com.nhnacademy.store99.bookstore.auth.repository.AuthRepository;
import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import com.nhnacademy.store99.bookstore.common.exception.AdminPermissionDeniedException;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 관리자 권한 체크 Service 구현체
 *
 * @author seunggyu-kim
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminCheckServiceImpl implements AdminCheckService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;

    /**
     * 사용자가 관리자인지 확인
     *
     * @param userId 사용자 ID
     * @return 관리자 여부
     */
    @Override
    public Boolean isAdmin(final Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new AdminPermissionDeniedException();
        }
        return "ADMIN".equals(authRepository.getAuth(userId));
    }
}

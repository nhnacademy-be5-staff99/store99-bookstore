package com.nhnacademy.store99.bookstore.auth.service.impl;

import com.nhnacademy.store99.bookstore.auth.repository.AuthRepository;
import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import com.nhnacademy.store99.bookstore.user.excepiton.UserNotFoundException;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminCheckServiceImpl implements AdminCheckService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;

    @Override
    public Boolean isAdmin(final Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        return "ADMIN".equals(authRepository.getAuth(userId));
    }
}

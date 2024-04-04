package com.nhnacademy.store99.bookstore.auth.service.impl;

import com.nhnacademy.store99.bookstore.auth.repository.AuthRepository;
import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminCheckServiceImpl implements AdminCheckService {
    private final AuthRepository authRepository;

    @Override
    public Boolean isAdmin(final Long userId) {
        return true;
    }
}

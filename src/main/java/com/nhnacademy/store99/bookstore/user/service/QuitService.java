package com.nhnacademy.store99.bookstore.user.service;

import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuitService {
    private final UserRepository userRepository;

    public void quit(Long userId) {
        userRepository.updateDeletedAtById(userId, LocalDateTime.now());
    }
}
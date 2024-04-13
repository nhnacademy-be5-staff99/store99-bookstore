package com.nhnacademy.store99.bookstore.auth.service.impl;

import com.nhnacademy.store99.bookstore.auth.repository.AuthRepository;
import com.nhnacademy.store99.bookstore.user.exception.UserNotFoundException;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * 관리자 여부 확인 서비스 테스트
 *
 * @author seunggyu-kim
 */
@ExtendWith(MockitoExtension.class)
class AdminCheckServiceImplTest {
    @InjectMocks
    private AdminCheckServiceImpl adminCheckService;

    @Mock
    private AuthRepository authRepository;

    @Mock
    private UserRepository userRepository;

    @DisplayName("관리자 여부 확인 - 관리자인 경우")
    @Test
    void isAdmin_true() {
        // given
        long userId = Mockito.anyLong();
        BDDMockito.given(userRepository.existsById(userId)).willReturn(true);
        BDDMockito.given(authRepository.getAuth(userId)).willReturn("ADMIN");

        // when
        Boolean result = adminCheckService.isAdmin(userId);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("관리자 여부 확인 - 관리자가 아닌 경우")
    @Test
    void isAdmin_false() {
        // given
        long userId = Mockito.anyLong();
        BDDMockito.given(userRepository.existsById(userId)).willReturn(true);
        BDDMockito.given(authRepository.getAuth(userId)).willReturn("USER");

        // when
        Boolean result = adminCheckService.isAdmin(userId);

        // then
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("관리자 여부 확인 - 사용자가 존재하지 않는 경우")
    @Test
    void isAdmin_userNotFound() {
        // given
        long userId = Mockito.anyLong();
        BDDMockito.given(userRepository.existsById(userId)).willReturn(false);

        // when, then
        Assertions.assertThatThrownBy(() -> adminCheckService.isAdmin(userId))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found (userId: %d)", userId);
    }
}
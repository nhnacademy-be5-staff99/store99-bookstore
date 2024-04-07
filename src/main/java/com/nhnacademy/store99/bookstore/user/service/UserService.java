package com.nhnacademy.store99.bookstore.user.service;

import com.nhnacademy.store99.bookstore.user.dto.AuthorizationRequest;
import com.nhnacademy.store99.bookstore.user.dto.AuthorizationResponse;
import com.nhnacademy.store99.bookstore.user.dto.UserAuthInfo;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 로그인 메소드
     * <p>
     * email 로 user 정보를 조회하고, 마지막 로그인 시간을 변경한다
     *
     * @param request
     * @return AuthorizationResponse
     */
    public AuthorizationResponse userLogin(AuthorizationRequest request) {
        String email = request.getEmail();

        // user table 에서 id, 권한 조회
        UserAuthInfo userAuthInfo = userRepository.getUserAuthInfo(email);

        AuthorizationResponse response = new AuthorizationResponse();
        response.setUserId(userAuthInfo.getId());
        response.setPassword(userAuthInfo.getConsumerPassword());
        response.setEmail(userAuthInfo.getConsumerEmail());
        response.setAuth(userAuthInfo.getAuth());

        // 마지막 login 일시 변경
        userRepository.updateLoginAt(userAuthInfo.getId(), LocalDateTime.now());

        return response;
    }

}

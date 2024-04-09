package com.nhnacademy.store99.bookstore.user.service;

import com.nhnacademy.store99.bookstore.user.dto.AuthorizationRequest;
import com.nhnacademy.store99.bookstore.user.dto.AuthorizationResponse;
import com.nhnacademy.store99.bookstore.user.dto.UserAuthInfoByEmail;
import com.nhnacademy.store99.bookstore.user.exception.UserAuthInfoNotFoundException;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
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
        Optional<UserAuthInfoByEmail> userAuthInfoByEmailOpt = userRepository.getUserAuthInfoByEmail(email);

        if (userAuthInfoByEmailOpt.isEmpty()) {
            throw new UserAuthInfoNotFoundException("email 로 user 정보 조회 실패");
        }

        UserAuthInfoByEmail userAuthInfoByEmail = userAuthInfoByEmailOpt.get();
        log.debug(userAuthInfoByEmail.toString());

        AuthorizationResponse response = new AuthorizationResponse();
        response.setUserId(userAuthInfoByEmail.getId());
        response.setPassword(userAuthInfoByEmail.getConsumerPassword());
        response.setEmail(userAuthInfoByEmail.getConsumerEmail());
        response.setAuth(userAuthInfoByEmail.getAuth());

        // 마지막 login 일시 변경
        userRepository.updateLoginAt(userAuthInfoByEmail.getId(), LocalDateTime.now());

        return response;
    }

}

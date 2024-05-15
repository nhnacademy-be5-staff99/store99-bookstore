package com.nhnacademy.store99.bookstore.user.service;

import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import com.nhnacademy.store99.bookstore.consumer.repository.ConsumerRepository;
import com.nhnacademy.store99.bookstore.user.dto.AuthorizationRequest;
import com.nhnacademy.store99.bookstore.user.dto.AuthorizationResponse;
import com.nhnacademy.store99.bookstore.user.dto.UserAuthInfoByEmail;
import com.nhnacademy.store99.bookstore.user.exception.UserNotFoundByEmailException;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ahyeon Song
 */
@Slf4j
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ConsumerRepository consumerRepository;

    public UserService(UserRepository userRepository, ConsumerRepository consumerRepository) {
        this.userRepository = userRepository;
        this.consumerRepository = consumerRepository;
    }

    /**
     * 실제 Db 에서 User 정보를 가져오는 로그인 메소드
     * <p>email 로 user 정보를 조회하고, 마지막 로그인 시간을 변경
     *
     * @param request
     * @return AuthorizationResponse
     */
    public AuthorizationResponse userLogin(AuthorizationRequest request) {
        String email = request.getEmail();

        // user table 에서 id, pw, 권한 조회
        Optional<UserAuthInfoByEmail> userAuthInfoByEmailOpt = userRepository.getUserAuthInfoByEmail(email);

        if (userAuthInfoByEmailOpt.isEmpty()) {
            throw new UserNotFoundByEmailException(email);
        }

        UserAuthInfoByEmail userAuthInfoByEmail = userAuthInfoByEmailOpt.get();
        log.debug("user auth info : {}", userAuthInfoByEmail);

        AuthorizationResponse response = new AuthorizationResponse();
        response.setUserId(userAuthInfoByEmail.getId());
        response.setPassword(userAuthInfoByEmail.getConsumerPassword());
        response.setEmail(userAuthInfoByEmail.getConsumerEmail());
        response.setAuth(userAuthInfoByEmail.getAuth());

        // 마지막 login 일시 변경
        userRepository.updateLoginAt(userAuthInfoByEmail.getId(), LocalDateTime.now());

        return response;
    }

    public boolean isDeleted(String email) {
        try {
            Consumer consumer = consumerRepository.findByConsumerEmail(email);
            return userRepository.existsByIdAndDeletedAtIsNotNull(consumer.getId());
        } catch (Exception e) {
            log.error("isDeleted 호출 중 오류 발생", e);
            throw e; // 필요에 따라 예외를 다시 던지거나, 적절한 처리를 수행

        }
    }

}

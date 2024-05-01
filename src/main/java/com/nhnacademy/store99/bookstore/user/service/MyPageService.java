package com.nhnacademy.store99.bookstore.user.service;

import com.nhnacademy.store99.bookstore.user.dto.MainMyPageResponse;
import com.nhnacademy.store99.bookstore.user.exception.UserNotFoundException;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * mypage 와 관련된 로직
 *
 * @author Ahyeon Song
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;

    /**
     * 메인 마이페이지에 들어가는 정보
     * <br>이름, 이메일, 주소, 연락처, 생일, 등급, 포인트
     *
     * @param xUserId
     * @return
     */
    public MainMyPageResponse getMainMyPage(Long xUserId) {
        Optional<MainMyPageResponse> myPageResponse = userRepository.getMainMyPageByUserId(xUserId);
        if (myPageResponse.isEmpty()) {
            throw new UserNotFoundException(xUserId);
        }
        return myPageResponse.get();
    }
}

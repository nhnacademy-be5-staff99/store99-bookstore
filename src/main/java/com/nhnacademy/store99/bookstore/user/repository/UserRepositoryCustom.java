package com.nhnacademy.store99.bookstore.user.repository;

import com.nhnacademy.store99.bookstore.user.dto.MainMyPageResponse;
import com.nhnacademy.store99.bookstore.user.dto.UserAuthInfoByEmail;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Ahyeon Song
 */
@NoRepositoryBean
public interface UserRepositoryCustom {
    Optional<UserAuthInfoByEmail> getUserAuthInfoByEmail(String email);

    void updateLoginAt(Long id, LocalDateTime loginAt);

    Optional<MainMyPageResponse> getMainMyPageByUserId(Long userId);

}

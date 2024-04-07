package com.nhnacademy.store99.bookstore.user.repository;

import com.nhnacademy.store99.bookstore.user.dto.UserAuthInfo;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepositoryCustom {
    Optional<UserAuthInfo> getUserAuthInfo(String email);

    void updateLoginAt(Long id, LocalDateTime loginAt);

}

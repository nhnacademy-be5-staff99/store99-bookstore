package com.nhnacademy.store99.bookstore.user.repository;

import com.nhnacademy.store99.bookstore.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 * <p>JPA Repository for User Entity
 *
 * @author seunggyu-kim
 * @author Ahyeon Song
 */
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    boolean existsByIdAndAuth_AuthName(Long userId, String authName);

    /**
     * userId 가 일치하고 휴면 상태가 아닌(활성상태) 유저 검색
     * <p>휴면 상태의 경우 optional 이 empty 임
     *
     * @param userId
     * @return
     */
    Optional<User> findByIdAndUserIsInactiveFalse(Long userId);
}

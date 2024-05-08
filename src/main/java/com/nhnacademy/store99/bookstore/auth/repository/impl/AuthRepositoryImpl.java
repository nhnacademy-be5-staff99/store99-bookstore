package com.nhnacademy.store99.bookstore.auth.repository.impl;

import com.nhnacademy.store99.bookstore.auth.entity.Auth;
import com.nhnacademy.store99.bookstore.auth.entity.QAuth;
import com.nhnacademy.store99.bookstore.auth.repository.AuthRepositoryCustom;
import com.nhnacademy.store99.bookstore.user.entity.QUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * Querydsl을 사용한 AuthRepositoryCustom 구현체
 *
 * @author seunggyu-kim
 */
public class AuthRepositoryImpl extends QuerydslRepositorySupport implements AuthRepositoryCustom {
    public AuthRepositoryImpl() {
        super(Auth.class);
    }


    /**
     * 사용자의 권한을 조회한다.
     *
     * @param userId 사용자 ID
     * @return 사용자의 권한
     */
    @Override
    public String getAuth(final Long userId) {
        QAuth auth = QAuth.auth;
        QUser user = QUser.user;

        return from(auth)
                .innerJoin(user).on(auth.eq(user.auth))
                .where(user.id.eq(userId))
                .select(auth.authName)
                .fetchOne();
    }
}
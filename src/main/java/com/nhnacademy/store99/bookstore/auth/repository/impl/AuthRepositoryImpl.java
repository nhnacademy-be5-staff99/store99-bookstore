package com.nhnacademy.store99.bookstore.auth.repository.impl;

import com.nhnacademy.store99.bookstore.auth.entity.Auth;
import com.nhnacademy.store99.bookstore.auth.entity.QAuth;
import com.nhnacademy.store99.bookstore.auth.repository.AuthRepositoryCustom;
import com.nhnacademy.store99.bookstore.user.entity.QUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class AuthRepositoryImpl extends QuerydslRepositorySupport implements AuthRepositoryCustom {
    public AuthRepositoryImpl() {
        super(Auth.class);
    }

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
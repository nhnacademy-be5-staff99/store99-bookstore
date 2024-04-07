package com.nhnacademy.store99.bookstore.user.repository;

import com.nhnacademy.store99.bookstore.auth.entity.QAuth;
import com.nhnacademy.store99.bookstore.consumer.entity.QConsumer;
import com.nhnacademy.store99.bookstore.user.dto.UserAuthInfo;
import com.nhnacademy.store99.bookstore.user.entity.QUser;
import com.nhnacademy.store99.bookstore.user.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<UserAuthInfo> getUserAuthInfo(String email) {
        QUser user = QUser.user;
        QConsumer consumer = QConsumer.consumer;
        QAuth auth = QAuth.auth;

        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(UserAuthInfo.class,
                        user.id,
                        consumer.consumerEmail,
                        consumer.consumerPassword,
                        user.auth.authName))
                .from(user)
                .join(user.consumers, consumer)
                .join(user.auth, auth)
                .where(consumer.consumerEmail.eq(email))
                .fetchOne());

    }

    @Override
    public void updateLoginAt(Long id, LocalDateTime loginAt) {
        QUser user = QUser.user;

        queryFactory
                .update(user)
                .set(user.userLoginAt, loginAt)
                .where(user.id.eq(id))
                .execute();
    }
}

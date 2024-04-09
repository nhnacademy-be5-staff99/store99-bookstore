package com.nhnacademy.store99.bookstore.user.repository;

import com.nhnacademy.store99.bookstore.auth.entity.QAuth;
import com.nhnacademy.store99.bookstore.consumer.entity.QConsumer;
import com.nhnacademy.store99.bookstore.user.dto.UserAuthInfoByEmail;
import com.nhnacademy.store99.bookstore.user.entity.QUser;
import com.nhnacademy.store99.bookstore.user.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * 유저 Db 접근을 위한 Query Dsl
 * @author Ahyeon Song
 */
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    /**
     * Email 로 User 정보 조회
     *
     * @param email
     * @return UserAuthInfoByEmail (userId, password, email, auth)
     */
    @Override
    public Optional<UserAuthInfoByEmail> getUserAuthInfoByEmail(String email) {
        QUser user = QUser.user;
        QConsumer consumer = QConsumer.consumer;
        QAuth auth = QAuth.auth;

        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(UserAuthInfoByEmail.class,
                        user.id,
                        consumer.consumerPassword,
                        consumer.consumerEmail,
                        user.auth.authName))
                .from(user)
                .join(user.consumers, consumer)
                .join(user.auth, auth)
                .where(consumer.consumerEmail.eq(email))
                .fetchOne());

    }

    /**
     * 로그인 성공 시 userId 에 해당하는 유저의 로그인 일시 최신화
     *
     * @param id
     * @param loginAt
     */
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

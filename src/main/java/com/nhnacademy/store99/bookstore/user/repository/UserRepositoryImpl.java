package com.nhnacademy.store99.bookstore.user.repository;

import com.nhnacademy.store99.bookstore.address.entity.QAddress;
import com.nhnacademy.store99.bookstore.auth.entity.QAuth;
import com.nhnacademy.store99.bookstore.consumer.entity.QConsumer;
import com.nhnacademy.store99.bookstore.grade.entity.QGrade;
import com.nhnacademy.store99.bookstore.user.dto.MainMyPageResponse;
import com.nhnacademy.store99.bookstore.user.dto.UserAuthInfoByEmail;
import com.nhnacademy.store99.bookstore.user.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 유저 Db 접근을 위한 Query Dsl
 *
 * @author Ahyeon Song
 */
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl() {
        this.queryFactory = null;
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

    /**
     * id 로 기본 마이페이지에 들어가는 정보 검색
     * <br>이름, 이메일, 주소, 연락처, 생일, 등급, 포인트
     *
     * @param userId
     */
    @Override
    public Optional<MainMyPageResponse> getMainMyPageByUserId(Long userId) {
        QUser user = QUser.user;
        QConsumer consumer = QConsumer.consumer;
        QGrade grade = QGrade.grade;
        QAddress address = QAddress.address;

        return Optional.ofNullable(
                from(user)
                        .join(user.consumers, consumer)
                        .join(user.grade, grade)
                        .leftJoin(address).on(user.eq(address.user))
                        .where(user.id.eq(userId)
                                .and(address.isDefaultAddress.isTrue()))
                        .select(Projections.constructor(MainMyPageResponse.class,
                                user.id,
                                consumer.consumerName,
                                consumer.consumerEmail,
                                consumer.consumerPhone,
                                user.userBirthdate,
                                user.userPoint,
                                Projections.constructor(MainMyPageResponse.UserAddress.class,
                                        address.addressGeneral,
                                        address.addressDetail,
                                        address.addressAlias,
                                        address.addressCode
                                ),
                                Projections.constructor(MainMyPageResponse.UserGrade.class,
                                        grade.gradeName,
                                        grade.gradeStartCost,
                                        grade.gradeEndCost,
                                        grade.gradeRatio)))
                        .fetchOne()

        );

    }
}

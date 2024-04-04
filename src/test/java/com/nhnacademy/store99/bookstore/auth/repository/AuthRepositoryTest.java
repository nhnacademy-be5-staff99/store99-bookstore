package com.nhnacademy.store99.bookstore.auth.repository;

import com.nhnacademy.store99.bookstore.auth.entity.Auth;
import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import com.nhnacademy.store99.bookstore.grade.entity.Grade;
import com.nhnacademy.store99.bookstore.user.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class AuthRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthRepository authRepository;

    @Test
    public void getAuth() {
        // given
        Grade grade = Grade.builder()
                .gradeName("BASIC")
                .gradeStartCost(0)
                .gradeEndCost(10000)
                .gradeRatio(3)
                .build();
        Grade persistedGrade = entityManager.persist(grade);

        Auth userAuthData = Auth.builder()
                .authName("USER")
                .build();
        Auth persistedUserAuth = entityManager.persist(userAuthData);

        Auth adminAuthData = Auth.builder()
                .authName("ADMIN")
                .build();
        Auth persistedAdminAuth = entityManager.persist(adminAuthData);

        Consumer consumer1 = Consumer.builder()
                .consumerName("consumer1 name")
                .consumerEmail("test1@test.com")
                .consumerPhone("01012345678")
                .consumerPassword("12345")
                .build();
        Consumer persistedConsumer1 = entityManager.persist(consumer1);

        Consumer consumer2 = Consumer.builder()
                .consumerName("consumer2 name")
                .consumerEmail("test2@test.com")
                .consumerPhone("01012345678")
                .consumerPassword("12345")
                .build();
        Consumer persistedConsumer2 = entityManager.persist(consumer2);

        User user = User.builder()
                .userBirthdate(LocalDate.now())
                .grade(persistedGrade)
                .userLoginAt(LocalDateTime.now())
                .auth(persistedUserAuth)
                .consumers(persistedConsumer1)
                .build();
        User persistedUser = entityManager.persist(user);

        User admin = User.builder()
                .userBirthdate(LocalDate.now())
                .grade(persistedGrade)
                .userLoginAt(LocalDateTime.now())
                .auth(persistedAdminAuth)
                .consumers(persistedConsumer2)
                .build();
        User persistedAdmin = entityManager.persist(admin);

        // when
        String userAuth = authRepository.getAuth(persistedUser.getId());
        String adminAuth = authRepository.getAuth(persistedAdmin.getId());

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(userAuth).isEqualTo(userAuthData.getAuthName());
            softly.assertThat(adminAuth).isEqualTo(adminAuthData.getAuthName());
        });
    }
}
package com.nhnacademy.store99.bookstore.user.repository;

import com.nhnacademy.store99.bookstore.auth.entity.Auth;
import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import com.nhnacademy.store99.bookstore.grade.entity.Grade;
import com.nhnacademy.store99.bookstore.user.dto.UserAuthInfoByEmail;
import com.nhnacademy.store99.bookstore.user.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @DisplayName("email 로 사용자 정보 조회")
    @Test
    public void getUserAuthInfoByEmailTest() {
        //given
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

        Consumer consumer = Consumer.builder()
                .consumerName("test consumer")
                .consumerEmail("test1@test.com")
                .consumerPhone("01012345678")
                .consumerPassword("12345")
                .build();
        Consumer persistedConsumer = entityManager.persist(consumer);

        User user = User.builder()
                .userBirthdate(LocalDate.now())
                .grade(persistedGrade)
                .auth(persistedUserAuth)
                .consumers(persistedConsumer)
                .build();
        User persistedUser = entityManager.persist(user);

        UserAuthInfoByEmail expectedUserInfo =
                new UserAuthInfoByEmail(user.getId(), user.getConsumers().getConsumerPassword(),
                        user.getConsumers().getConsumerEmail(), user.getAuth().getAuthName());


        //when
        Optional<UserAuthInfoByEmail> userAuthInfoByEmailOpt =
                userRepository.getUserAuthInfoByEmail(persistedUser.getConsumers().getConsumerEmail());

        UserAuthInfoByEmail userAuthInfoByEmail = userAuthInfoByEmailOpt.get();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(userAuthInfoByEmail.getId()).as("Check id").isEqualTo(expectedUserInfo.getId());
            softly.assertThat(userAuthInfoByEmail.getConsumerPassword()).as("Check password")
                    .isEqualTo(expectedUserInfo.getConsumerPassword());
            softly.assertThat(userAuthInfoByEmail.getConsumerEmail()).as("Check email")
                    .isEqualTo(expectedUserInfo.getConsumerEmail());
            softly.assertThat(userAuthInfoByEmail.getAuth()).as("Check authName").isEqualTo(expectedUserInfo.getAuth());
        });

    }

    @DisplayName("userId 로 마지막 로그인 시간 업데이트")
    @Test
    public void updateLoginAt() {
        //given
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

        Consumer consumer = Consumer.builder()
                .consumerName("test consumer")
                .consumerEmail("test1@test.com")
                .consumerPhone("01012345678")
                .consumerPassword("12345")
                .build();
        Consumer persistedConsumer = entityManager.persist(consumer);

        User user = User.builder()
                .userBirthdate(LocalDate.now())
                .grade(persistedGrade)
                .auth(persistedUserAuth)
                .consumers(persistedConsumer)
                .build();
        User persistedUser = entityManager.persist(user);

        //when
        userRepository.updateLoginAt(persistedUser.getId(), LocalDateTime.now());
        entityManager.flush();
        entityManager.clear();

        //then
        User updatedUser = userRepository.findById(persistedUser.getId()).get();
        Assertions.assertNotNull(updatedUser.getUserLoginAt());

    }

}
package com.nhnacademy.store99.bookstore.user.service;


import com.nhnacademy.store99.bookstore.auth.entity.Auth;
import com.nhnacademy.store99.bookstore.auth.repository.AuthRepository;
import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import com.nhnacademy.store99.bookstore.consumer.repository.ConsumerRepository;
import com.nhnacademy.store99.bookstore.grade.entity.Grade;
import com.nhnacademy.store99.bookstore.grade.repository.GradeRepository;
import com.nhnacademy.store99.bookstore.user.dto.SignUpDto;
import com.nhnacademy.store99.bookstore.user.entity.User;
import com.nhnacademy.store99.bookstore.user.repository.SignUpRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignUpService {

    private final SignUpRepository userRepository;
    private final ConsumerRepository consumerRepository;
    private final AuthRepository authRepository;
    private final GradeRepository gradeRepository;

    @Autowired
    public SignUpService(SignUpRepository userRepository, ConsumerRepository consumerRepository,
                         AuthRepository authRepository, GradeRepository gradeRepository) {
        this.userRepository = userRepository;
        this.consumerRepository = consumerRepository;
        this.authRepository = authRepository;
        this.gradeRepository = gradeRepository;
    }

    public boolean duplicateCheck(String password) {
        return !consumerRepository.existsByConsumerPassword(password);
    }

    @Transactional
    public void signUp(SignUpDto signUpDto) {

        Consumer consumer = Consumer.builder()
                .consumerName(signUpDto.getName())
                .consumerEmail(signUpDto.getEmail())
                .consumerPhone(signUpDto.getPhoneNumber())
                .consumerPassword(signUpDto.getPassword())
                .build();
        consumerRepository.save(consumer);

        Auth auth = Auth.builder()
                .authName("ROLE_USER")
                .build();
        authRepository.save(auth);

        Grade grade = Grade.builder()
                .gradeName("BASIC")
                .gradeStartCost(0)
                .gradeEndCost(100)
                .gradeRatio(5)
                .build();
        gradeRepository.save(grade);

        User user = User.builder()
                .userBirthdate(signUpDto.getUserBirthDate())
                .consumers(consumer)
                .grade(grade)
                .auth(auth)
                .userPoint(1000000)
                .userLoginAt(LocalDateTime.now())
                .userIsInactive(false)
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

    }

}

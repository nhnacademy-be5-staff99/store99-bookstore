package com.nhnacademy.store99.bookstore.user.service;


import static com.nhnacademy.store99.bookstore.point_history.enums.PointHistoryType.WELCOME;

import com.nhnacademy.store99.bookstore.address.entity.Address;
import com.nhnacademy.store99.bookstore.address.repository.AddressRepository;
import com.nhnacademy.store99.bookstore.auth.entity.Auth;
import com.nhnacademy.store99.bookstore.auth.repository.AuthRepository;
import com.nhnacademy.store99.bookstore.consumer.entity.Consumer;
import com.nhnacademy.store99.bookstore.consumer.repository.ConsumerRepository;
import com.nhnacademy.store99.bookstore.grade.entity.Grade;
import com.nhnacademy.store99.bookstore.grade.repository.GradeRepository;
import com.nhnacademy.store99.bookstore.point_history.entity.PointHistory;
import com.nhnacademy.store99.bookstore.point_history.repository.PointRepository;
import com.nhnacademy.store99.bookstore.point_policies.entity.PointPolicyRepository;
import com.nhnacademy.store99.bookstore.user.dto.SignUpDto;
import com.nhnacademy.store99.bookstore.user.entity.User;
import com.nhnacademy.store99.bookstore.user.repository.SignUpRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jinhyogyeom
 */

@Service
public class SignUpService {

    private final SignUpRepository userRepository;
    private final ConsumerRepository consumerRepository;
    private final AuthRepository authRepository;
    private final GradeRepository gradeRepository;
    private final AddressRepository addressRepository;
    private final PointRepository pointRepository;

    private final PointPolicyRepository pointPolicyRepository;


    @Autowired
    public SignUpService(SignUpRepository userRepository, ConsumerRepository consumerRepository,
                         AuthRepository authRepository, GradeRepository gradeRepository,
                         AddressRepository addressRepository, PointRepository pointRepository,
                         PointPolicyRepository pointPolicyRepository) {
        this.userRepository = userRepository;
        this.consumerRepository = consumerRepository;
        this.authRepository = authRepository;
        this.gradeRepository = gradeRepository;
        this.addressRepository = addressRepository;
        this.pointRepository = pointRepository;
        this.pointPolicyRepository = pointPolicyRepository;
    }

    /**
     * 실제 Db의 password값과 비교해서 password 중복 체크하는 메소드
     *
     * @param email
     * @return boolean
     */
    public String duplicateCheck(String email) {
        if (consumerRepository.existsByConsumerEmail(email)) {
            return "true";
        } else {
            return "false";
        }
    }

    /**
     * 회원가입 메소드
     *
     * @param signUpDto
     * @return void
     */
    @Transactional
    public SignUpDto signUp(SignUpDto signUpDto) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(signUpDto.getPassword());

        Consumer consumer = Consumer.builder()
                .consumerName(signUpDto.getName())
                .consumerEmail(signUpDto.getEmail())
                .consumerPhone(signUpDto.getPhoneNumber())
                .consumerPassword(hashedPassword)
                .build();
        consumerRepository.save(consumer);


        User user = User.builder()
                .userBirthdate(signUpDto.getUserBirthDate())
                .consumers(consumer)
                .grade(Grade.builder().id(1L).build())
                .auth(Auth.builder().id(1L).build())
                .userPoint(pointPolicyRepository.findPointValueByPolicyType("WELCOME").getPointValue())
                .userLoginAt(LocalDateTime.now())
                .userIsInactive(false)
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        Address address = Address.builder()
                .addressGeneral(signUpDto.getAddress().getAddressGeneral())
                .addressDetail(signUpDto.getAddress().getAddressDetail())
                .addressAlias(signUpDto.getAddress().getAddressAlias())
                .addressCode(signUpDto.getAddress().getAddressCode())
                .isDefaultAddress(true)
                .user(user)
                .build();
        addressRepository.save(address);

        PointHistory pointHistory = PointHistory.builder()
                .user(user)
                .pointHistoryValue(pointPolicyRepository.findPointValueByPolicyType("WELCOME").getPointValue())
                .pointHistoryType(WELCOME)
                .createdAt(LocalDateTime.now())
                .build();
        pointRepository.save(pointHistory);
        return signUpDto;
    }

}

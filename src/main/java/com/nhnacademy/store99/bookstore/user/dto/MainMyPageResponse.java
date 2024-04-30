package com.nhnacademy.store99.bookstore.user.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 메인 마이페이지 화면 구성을 위한 응답
 *
 * @author Ahyeon Song
 */
@Getter
@AllArgsConstructor
@Builder
public class MainMyPageResponse {
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
    private LocalDate userBirthDay;
    private Integer userPoint;
    private UserAddress userAddress;
    private UserGrade userGrade;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UserAddress {
        private String addressGeneral;
        private String addressDetail;
        private String addressAlias;
        private Integer addressCode;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UserGrade {
        private String gradeName;
        private Integer gradeStartCost;
        private Integer gradeEndCost;
        private Integer gradeRatio;
    }

}

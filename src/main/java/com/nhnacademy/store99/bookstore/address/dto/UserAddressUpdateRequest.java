package com.nhnacademy.store99.bookstore.address.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원의 주소 수정 요청 형식
 *
 * @author Ahyeon Song
 */
@AllArgsConstructor
@Getter
public class UserAddressUpdateRequest {
    private Long addressId;
    private String addressGeneral;
    private String addressDetail;
    private String addressAlias;
    private Integer addressCode;
}

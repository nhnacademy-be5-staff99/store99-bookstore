package com.nhnacademy.store99.bookstore.address.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * default 주소를 변경하기 위한 요청 형식
 *
 * @author Ahyeon Song
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserChangeDefaultAddressRequest {
    private Long addressId;
}

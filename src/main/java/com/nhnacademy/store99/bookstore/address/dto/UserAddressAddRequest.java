package com.nhnacademy.store99.bookstore.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * address 추가를 위한 형식
 *
 * @author Ahyeon Song
 */
@AllArgsConstructor
@Getter
@Builder
public class UserAddressAddRequest {
    private String addressGeneral;
    private String addressDetail;
    private String addressAlias;
    private Integer addressCode;
}

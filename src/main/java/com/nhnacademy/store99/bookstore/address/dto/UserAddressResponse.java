package com.nhnacademy.store99.bookstore.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserAddressResponse {
    private Long addressId;
    private String addressGeneral;
    private String addressDetail;
    private String addressAlias;
    private Integer addressCode;
    private Boolean isDefaultAddress;
}

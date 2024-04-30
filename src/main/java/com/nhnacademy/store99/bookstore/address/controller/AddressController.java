package com.nhnacademy.store99.bookstore.address.controller;

import com.nhnacademy.store99.bookstore.address.dto.UserAddressResponse;
import com.nhnacademy.store99.bookstore.address.service.AddressService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원의 주소를 조회하고 관리
 *
 * @author Ahyeon Song
 */
@RestController
@RequestMapping("/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    /**
     * header 의 X-USER-ID 에 해당하는 회원의 주소 리스트 조회
     *
     * @param xUserId
     * @return UserAddressResponse
     */
    @GetMapping
    public List<UserAddressResponse> getUserAddresses(@RequestHeader("X-USER-ID") Long xUserId) {
        return addressService.getUserAddresses(xUserId);
    }

}

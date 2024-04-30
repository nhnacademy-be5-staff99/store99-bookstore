package com.nhnacademy.store99.bookstore.address.controller;

import com.nhnacademy.store99.bookstore.address.dto.UserAddressAddRequest;
import com.nhnacademy.store99.bookstore.address.dto.UserAddressResponse;
import com.nhnacademy.store99.bookstore.address.dto.UserAddressUpdateRequest;
import com.nhnacademy.store99.bookstore.address.dto.UserChangeDefaultAddressRequest;
import com.nhnacademy.store99.bookstore.address.service.AddressService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 주소 추가
     *
     * @param xUserId
     * @param request
     */
    @PostMapping
    public ResponseEntity<CommonResponse<Void>> addUserAddress(@RequestHeader("X-USER-ID") Long xUserId,
                                                               @RequestBody UserAddressAddRequest request) {
        Long newAddressId = addressService.addUserAddress(xUserId, request);

        CommonHeader header = CommonHeader.builder().httpStatus(HttpStatus.CREATED).build();
        CommonResponse<Void> response = CommonResponse.<Void>builder().header(header).build();

        return ResponseEntity.created(URI.create("/v1/address/" + newAddressId)).body(response);
    }

    /**
     * 주소 수정
     *
     * @param xUserId
     * @param request
     */
    @PatchMapping("/update")
    public void updateUserAddress(@RequestHeader("X-USER-ID") Long xUserId,
                                  @RequestBody UserAddressUpdateRequest request) {
        addressService.updateUserAddress(xUserId, request);
    }

    /**
     * addressId 에 해당하는 주소를 기본 주소로 변경
     *
     * @param xUserId
     * @param addressId
     */
    @PatchMapping("/change_default")
    public void updateDefaultAddress(@RequestHeader("X-USER-ID") Long xUserId, @RequestBody
    UserChangeDefaultAddressRequest request) {
        addressService.updateDefaultAddress(xUserId, request);

    }

    /**
     * addressId 에 해당하는 주소 삭제
     *
     * @param xUserId
     * @param addressId
     */
    @DeleteMapping("/{addressId}")
    public void deleteUserAddress(@RequestHeader("X-USER-ID") Long xUserId, @PathVariable("addressId") Long addressId) {
        addressService.deleteUserAddress(xUserId, addressId);
    }

}

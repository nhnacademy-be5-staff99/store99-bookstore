package com.nhnacademy.store99.bookstore.address.service;

import com.nhnacademy.store99.bookstore.address.dto.UserAddressResponse;
import com.nhnacademy.store99.bookstore.address.exception.AddressNotFoundException;
import com.nhnacademy.store99.bookstore.address.repository.AddressRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 주소를 조회하고 관리
 *
 * @author Ahyeon Song
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    /**
     * 유저 아이디에 해당하는 주소 리스트 반환
     *
     * @param xUserId
     * @return UserAddressResponse
     */
    public List<UserAddressResponse> getUserAddresses(Long xUserId) {
        List<UserAddressResponse> userAddresses = addressRepository.getUserAddresses(xUserId);
        if (userAddresses.isEmpty()) {
            throw new AddressNotFoundException(xUserId);
        }
        return userAddresses;
    }

}

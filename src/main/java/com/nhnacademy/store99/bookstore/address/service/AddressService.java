package com.nhnacademy.store99.bookstore.address.service;

import com.nhnacademy.store99.bookstore.address.dto.UserAddressAddRequest;
import com.nhnacademy.store99.bookstore.address.dto.UserAddressResponse;
import com.nhnacademy.store99.bookstore.address.dto.UserAddressUpdateRequest;
import com.nhnacademy.store99.bookstore.address.dto.UserChangeDefaultAddressRequest;
import com.nhnacademy.store99.bookstore.address.entity.Address;
import com.nhnacademy.store99.bookstore.address.exception.AddressNotFoundByAddressIdException;
import com.nhnacademy.store99.bookstore.address.exception.AddressNotFoundByUserIdException;
import com.nhnacademy.store99.bookstore.address.exception.AddressOverTenException;
import com.nhnacademy.store99.bookstore.address.exception.DefaultAddressCanNotDeleteException;
import com.nhnacademy.store99.bookstore.address.repository.AddressRepository;
import com.nhnacademy.store99.bookstore.user.exception.UserNotFoundException;
import com.nhnacademy.store99.bookstore.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
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
    private final UserRepository userRepository;

    /**
     * 유저 아이디에 해당하는 주소 리스트 반환
     *
     * @param xUserId
     * @return UserAddressResponse
     */
    public List<UserAddressResponse> getUserAddresses(Long xUserId) {
        List<UserAddressResponse> userAddresses = addressRepository.getUserAddresses(xUserId);
        if (userAddresses.isEmpty()) {
            throw new AddressNotFoundByUserIdException(xUserId);
        }
        return userAddresses;
    }

    /**
     * 주소 아이디에 해당하는 주소 반환
     *
     * @param xUserId
     * @param addressId
     * @return UserAddressResponse
     */
    public UserAddressResponse getUserAddressById(Long xUserId, Long addressId) {
        boolean isUser = userRepository.existsByIdAndAuth_AuthName(xUserId, "USER");
        if (!isUser) {
            throw new UserNotFoundException(xUserId);
        }
        Optional<Address> findAddressOpt = addressRepository.findById(addressId);

        if (findAddressOpt.isEmpty()) {
            throw new AddressNotFoundByAddressIdException(addressId);
        }
        Address findAddress = findAddressOpt.get();

        return new UserAddressResponse(
                findAddress.getId(),
                findAddress.getAddressGeneral(),
                findAddress.getAddressDetail(),
                findAddress.getAddressAlias(),
                findAddress.getAddressCode(),
                findAddress.getIsDefaultAddress()
        );
    }

    /**
     * 주소 추가
     *
     * <p>주소 개수가 10개 이상이 될 수 없음
     */
    @Transactional
    public Long addUserAddress(Long xUserId, UserAddressAddRequest request) {
        Integer addressCnt = addressRepository.countAllByUser_Id(xUserId);
        if (addressCnt >= 10) {
            throw new AddressOverTenException(xUserId);
        }
        return addressRepository.addUserAddress(xUserId, request);
    }

    /**
     * 주소 수정
     *
     * @param xUserId
     * @param request
     */
    @Transactional
    public void updateUserAddress(Long xUserId, UserAddressUpdateRequest request) {
        boolean isUser = userRepository.existsByIdAndAuth_AuthName(xUserId, "USER");
        if (!isUser) {
            throw new UserNotFoundException(xUserId);
        }
        addressRepository.updateAddressByAddressId(request.getAddressId(), request);
    }

    /**
     * 선택한 주소를 기본 주소로 변경
     */
    @Transactional
    public void updateDefaultAddress(Long xUserId, UserChangeDefaultAddressRequest request) {
        boolean isUser = userRepository.existsByIdAndAuth_AuthName(xUserId, "USER");
        if (!isUser) {
            throw new UserNotFoundException(xUserId);
        }
        Long addressId = request.getAddressId();
        addressRepository.updateDefaultAddressByAddressId(xUserId, addressId);
    }

    /**
     * 주소 삭제
     *
     * <p>기본 주소로 설정되어 있다면 삭제하지 못함
     *
     * @param xUserId
     * @param addressId
     */
    @Transactional
    public void deleteUserAddress(Long xUserId, Long addressId) {
        boolean isUser = userRepository.existsByIdAndAuth_AuthName(xUserId, "USER");
        if (!isUser) {
            throw new UserNotFoundException(xUserId);
        }
        Boolean isDefaultAddress = addressRepository.checkDefaultAddressById(addressId);

        if (Boolean.TRUE.equals(isDefaultAddress)) {
            throw new DefaultAddressCanNotDeleteException(addressId);
        }

        addressRepository.deleteById(addressId);
    }

    /**
     * 현재 주소 개수 반환
     */
    public Integer getUserAddressCount(Long xUserId) {
        return addressRepository.countAllByUser_Id(xUserId);
    }

}

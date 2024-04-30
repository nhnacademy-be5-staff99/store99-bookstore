package com.nhnacademy.store99.bookstore.address.repository;

import com.nhnacademy.store99.bookstore.address.dto.UserAddressAddRequest;
import com.nhnacademy.store99.bookstore.address.dto.UserAddressResponse;
import com.nhnacademy.store99.bookstore.address.dto.UserAddressUpdateRequest;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AddressRepositoryCustom {
    List<UserAddressResponse> getUserAddresses(Long userId);

    Long addUserAddress(Long userId, UserAddressAddRequest request);

    Boolean checkDefaultAddressById(Long addressId);

    void updateAddressByAddressId(Long addressId, UserAddressUpdateRequest request);

    void updateDefaultAddressByAddressId(Long userId, Long addressId);
}

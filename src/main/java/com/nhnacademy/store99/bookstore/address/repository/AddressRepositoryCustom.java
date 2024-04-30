package com.nhnacademy.store99.bookstore.address.repository;

import com.nhnacademy.store99.bookstore.address.dto.UserAddressResponse;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AddressRepositoryCustom {
    List<UserAddressResponse> getUserAddresses(Long userId);
}

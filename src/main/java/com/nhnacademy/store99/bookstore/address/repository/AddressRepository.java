package com.nhnacademy.store99.bookstore.address.repository;

import com.nhnacademy.store99.bookstore.address.entity.Address;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahyeon Song
 */
public interface AddressRepository extends JpaRepository<Address, Long>, AddressRepositoryCustom {
    Integer countAllByUser_Id(Long userId);
    Optional<Address> findById(Long addressId);
    void deleteById(Long addressId);
}

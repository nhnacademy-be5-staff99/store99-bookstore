package com.nhnacademy.store99.bookstore.address.repository;

import com.nhnacademy.store99.bookstore.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long>, AddressRepositoryCustom {
}

package com.nhnacademy.store99.bookstore.address.repository.impl;

import com.nhnacademy.store99.bookstore.address.dto.UserAddressResponse;
import com.nhnacademy.store99.bookstore.address.entity.Address;
import com.nhnacademy.store99.bookstore.address.entity.QAddress;
import com.nhnacademy.store99.bookstore.address.repository.AddressRepositoryCustom;
import com.nhnacademy.store99.bookstore.user.entity.QUser;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * Query Dsl 을 사용해 address 조회 및 관리
 *
 * @author Ahyeon Song
 */
public class AddressRepositoryImpl extends QuerydslRepositorySupport implements AddressRepositoryCustom {
    public AddressRepositoryImpl() {
        super(Address.class);
    }

    /**
     * 마이페이지에서 회원의 주소를 보여주기 위해 주소 리스트 조회
     *
     * @param userId
     * @return UserAddressResponse - 도로명주소, 상세주소, 별칭, 우편번호
     */
    @Override
    public List<UserAddressResponse> getUserAddresses(Long userId) {
        QAddress address = QAddress.address;
        QUser user = QUser.user;

        return from(address)
                .leftJoin(user).on(user.eq(address.user))
                .where(user.id.eq(userId))
                .select(Projections.constructor(UserAddressResponse.class,
                        address.id.as("addressId"),
                        address.addressGeneral,
                        address.addressDetail,
                        address.addressAlias,
                        address.addressCode,
                        address.isDefaultAddress))
                .fetch();

    }
}

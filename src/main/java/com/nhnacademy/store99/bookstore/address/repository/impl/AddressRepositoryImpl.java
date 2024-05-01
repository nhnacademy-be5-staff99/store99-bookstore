package com.nhnacademy.store99.bookstore.address.repository.impl;

import com.nhnacademy.store99.bookstore.address.dto.UserAddressAddRequest;
import com.nhnacademy.store99.bookstore.address.dto.UserAddressResponse;
import com.nhnacademy.store99.bookstore.address.dto.UserAddressUpdateRequest;
import com.nhnacademy.store99.bookstore.address.entity.Address;
import com.nhnacademy.store99.bookstore.address.entity.QAddress;
import com.nhnacademy.store99.bookstore.address.exception.AddressNotFoundByAddressIdException;
import com.nhnacademy.store99.bookstore.address.repository.AddressRepositoryCustom;
import com.nhnacademy.store99.bookstore.user.entity.QUser;
import com.nhnacademy.store99.bookstore.user.entity.User;
import com.nhnacademy.store99.bookstore.user.exception.UserNotFoundException;
import com.querydsl.core.types.Projections;
import java.util.List;
import java.util.Objects;
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

    /**
     * 주소 추가
     *
     * @param userId
     * @param request
     */
    @Override
    public Long addUserAddress(Long userId, UserAddressAddRequest request) {
        QUser user = QUser.user;

        User findUser = from(user)
                .where(user.id.eq(userId))
                .fetchOne();

        if (findUser == null) {
            throw new UserNotFoundException(userId);
        }

        Address newAddress = Address.builder()
                .addressGeneral(request.getAddressGeneral())
                .addressDetail(request.getAddressDetail())
                .addressAlias(request.getAddressAlias())
                .addressCode(request.getAddressCode())
                .isDefaultAddress(false)
                .user(findUser)
                .build();

        Objects.requireNonNull(getEntityManager()).persist(newAddress);

        return newAddress.getId();
    }

    /**
     * addressId 가 default address 인지 확인
     *
     * @param addressId
     * @return Boolean
     */
    @Override
    public Boolean checkDefaultAddressById(Long addressId) {
        QAddress address = QAddress.address;

        return from(address)
                .where(address.id.eq(addressId))
                .select(address.isDefaultAddress)
                .fetchOne();
    }

    /**
     * addressId 에 해당하는 주소의 내용을 수정
     *
     * @param addressId
     * @param request
     */
    @Override
    public void updateAddressByAddressId(Long addressId, UserAddressUpdateRequest request) {
        QAddress address = QAddress.address;

        Address findAddress = from(address)
                .where(address.id.eq(addressId))
                .fetchOne();

        if (findAddress == null) {
            throw new AddressNotFoundByAddressIdException(addressId);
        }

        findAddress.updateAddress(
                request.getAddressGeneral(),
                request.getAddressDetail(),
                request.getAddressAlias(),
                request.getAddressCode()
        );
    }

    /**
     * addressId 에 해당하는 주소를 is_default_address : true 로 변경
     * <p>나머지 주소는 default is_default_address : false 로 변경
     *
     * @param addressId
     */
    @Override
    public void updateDefaultAddressByAddressId(Long userId, Long addressId) {
        QAddress address = QAddress.address;
        QUser user = QUser.user;

        // addressId 에 해당하는 주소를 is_default_address : true 로 변경
        Address defaultAddress = from(address)
                .where(address.id.eq(addressId))
                .where(address.user.id.eq(userId))
                .fetchOne();

        if (defaultAddress == null) {
            throw new AddressNotFoundByAddressIdException(addressId);
        }

        defaultAddress.setDefaultAddress(true);

        // 나머지 주소를 is_default_address : false 로 변경
        List<Address> nonDefaultAddresses = from(address)
                .where(address.id.ne(addressId))
                .where(address.user.id.eq(userId))
                .fetch();

        for (Address nonDefaultAddress : nonDefaultAddresses) {
            nonDefaultAddress.setDefaultAddress(false);
        }
    }
}

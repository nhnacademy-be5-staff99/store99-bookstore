package com.nhnacademy.store99.bookstore.address.exception;

/**
 * 기본 주소 삭제 시도 시 에러 처리
 *
 * @author Ahyeon Song
 */
public class DefaultAddressCanNotDeleteException extends RuntimeException {
    public DefaultAddressCanNotDeleteException(Long addressId) {
        super(String.format("기본 주소는 삭제할 수 없음 - addressId : %d", addressId));
    }
}

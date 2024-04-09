package com.nhnacademy.store99.bookstore.user.excepiton;

import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;

/**
 * 유저 정보를 찾을 수 없을 때 발생하는 예외
 *
 * @author seunggyu-kim
 */
public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Long userId) {
        super(String.format("User not found (userId: %d)", userId));
    }
}

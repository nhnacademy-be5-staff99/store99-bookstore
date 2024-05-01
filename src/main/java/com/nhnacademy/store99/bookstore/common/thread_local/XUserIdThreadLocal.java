package com.nhnacademy.store99.bookstore.common.thread_local;

import com.nhnacademy.store99.bookstore.common.exception.AuthenticationException;
import java.util.Objects;

/**
 * 유저 번호인 X_USER_ID를 ThreadLocal로 관리하는 클래스
 *
 * @author seunggyu-kim
 */
public class XUserIdThreadLocal {
    private static final ThreadLocal<Long> X_USER_ID = new ThreadLocal<>();

    private XUserIdThreadLocal() {
        throw new IllegalStateException("Utility class");
    }

    public static Long getXUserId() {
        Long userId = X_USER_ID.get();
        if (Objects.isNull(userId)) {
            throw new AuthenticationException("X-USER-ID가 존재하지 않습니다.");
        }
        return userId;
    }

    public static void setXUserId(Long xUserId) {
        X_USER_ID.set(xUserId);
    }

    public static void reset() {
        X_USER_ID.remove();
    }
}

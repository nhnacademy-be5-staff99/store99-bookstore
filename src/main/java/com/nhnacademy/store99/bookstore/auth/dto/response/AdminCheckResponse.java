package com.nhnacademy.store99.bookstore.auth.dto.response;

/**
 * 관리자 권한 체크 Response
 *
 * @author seunggyu-kim
 */
public class AdminCheckResponse {
    /**
     * 관리자 여부
     */
    private final Boolean isAdmin;

    public AdminCheckResponse(final Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }
}

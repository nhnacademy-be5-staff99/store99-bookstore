package com.nhnacademy.store99.bookstore.auth.dto.response;

public class AdminCheckResponse {
    private final Boolean isAdmin;

    public AdminCheckResponse(final Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }
}

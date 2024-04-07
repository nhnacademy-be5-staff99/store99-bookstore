package com.nhnacademy.store99.bookstore.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorizationResponse {
    private Long userId;
    private String password;
    private String email;
    private String auth;

}

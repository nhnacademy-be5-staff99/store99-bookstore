package com.nhnacademy.store99.bookstore.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserAuthInfo {
    private Long id;
    private String consumerPassword;
    private String consumerEmail;
    private String auth;
}

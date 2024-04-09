package com.nhnacademy.store99.bookstore.user.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ahyeon Song
 */
@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"userId", "password", "email", "auth"})
public class AuthorizationResponse {
    private Long userId;
    private String password;
    private String email;
    private String auth;

}

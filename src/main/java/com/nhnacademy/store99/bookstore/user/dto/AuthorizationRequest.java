package com.nhnacademy.store99.bookstore.user.dto;

import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Ahyeon Song
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationRequest {
    @Email
    private String email;
}

package com.nhnacademy.store99.bookstore.secure_key_manager.response;

import lombok.Setter;

/**
 * SecretBodyResponse
 * <p>Secure Key Manager의 기밀 데이터를 받기 위한 응답 객체의 Body
 *
 * @author seunggyu-kim
 */
@Setter
public class SecretBodyResponse {
    private String secret;

    public String getSecret() {
        return secret;
    }
}
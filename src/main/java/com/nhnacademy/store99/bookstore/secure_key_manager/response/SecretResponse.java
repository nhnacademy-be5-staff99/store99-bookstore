package com.nhnacademy.store99.bookstore.secure_key_manager.response;

import lombok.Setter;

/**
 * SecretResponse
 * <p>Secure Key Manager의 기밀 데이터를 받기 위한 응답 객체
 *
 * @author seunggyu-kim
 */
@Setter
public class SecretResponse {
    private SecretHeaderResponse header;
    private SecretBodyResponse body;

    public String getSecret() {
        return body.getSecret();
    }
}
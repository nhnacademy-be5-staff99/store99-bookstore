package com.nhnacademy.store99.bookstore.secure_key_manager.response;

import lombok.Setter;

/**
 * SecretHeaderResponse
 * <p>Secure Key Manager의 기밀 데이터를 받기 위한 응답 객체의 Header
 *
 * @author seunggyu-kim
 */
@Setter
public class SecretHeaderResponse {
    private int resultCode;
    private String resultMessage;
    private boolean isSuccessful;
}

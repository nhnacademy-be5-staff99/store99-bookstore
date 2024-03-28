package com.nhnacademy.store99.bookstore.secure_key_manager.response;

import lombok.Setter;

@Setter
public class SecretHeaderResponse {
    private int resultCode;
    private String resultMessage;
    private boolean isSuccessful;
}

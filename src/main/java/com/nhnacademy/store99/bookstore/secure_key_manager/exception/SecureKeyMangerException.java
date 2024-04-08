package com.nhnacademy.store99.bookstore.secure_key_manager.exception;

/**
 * Secure Key Manger Exception
 *
 * @author seunggyu-kim
 */
public class SecureKeyMangerException extends RuntimeException {
    public static final String MESSAGE = "Secure Key Manager 오류 발생 : ";

    public SecureKeyMangerException(String message) {
        super(MESSAGE + message);
    }
}

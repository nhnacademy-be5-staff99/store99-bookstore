package com.nhnacademy.store99.bookstore.secure_key_manager.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Secure Key Manager 설정 정보
 *
 * @author seunggyu-kim
 */
@Setter
@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "secure.key.manager")
public class SecureKeyManagerProperties {
    private String xTcAuthenticationId;
    private String xTcAuthenticationSecret;
    private String certificatePassword;
    private String appKey;
}

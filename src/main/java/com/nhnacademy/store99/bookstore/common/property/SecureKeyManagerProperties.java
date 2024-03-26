package com.nhnacademy.store99.bookstore.common.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

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

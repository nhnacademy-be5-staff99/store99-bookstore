package com.nhnacademy.store99.bookstore.config.property;

import com.nhnacademy.store99.bookstore.common.util.SecureKeyManagerUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * DataSource 설정 정보
 *
 * @author seunggyu-kim
 */
@Setter
@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "db")
public class DataSourceProperties {
    private final SecureKeyManagerUtil secureKeyManagerUtil;

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int initialSize;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;
    private long maxWaitMillis;
    private String validationQuery;
    private boolean testOnBorrow;

    /**
     * url이 secureKey로 암호화 되어있는 경우 복호화하여 설정
     *
     * @param url
     */
    public void setUrl(final String url) {
        if (secureKeyManagerUtil.isEncrypted(url)) {
            this.url = secureKeyManagerUtil.loadConfidentialData(url);
        } else {
            this.url = url;
        }
    }

    /**
     * username이 secureKey로 암호화 되어있는 경우 복호화하여 설정
     *
     * @param username
     */
    public void setUsername(final String username) {
        if (secureKeyManagerUtil.isEncrypted(username)) {
            this.username = secureKeyManagerUtil.loadConfidentialData(username);
        } else {
            this.username = username;
        }
    }

    /**
     * password가 secureKey로 암호화 되어있는 경우 복호화하여 설정
     *
     * @param password
     */
    public void setPassword(final String password) {
        if (secureKeyManagerUtil.isEncrypted(password)) {
            this.password = secureKeyManagerUtil.loadConfidentialData(password);
        } else {
            this.password = password;
        }
    }
}

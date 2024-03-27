package com.nhnacademy.store99.bookstore.common.config;

import com.nhnacademy.store99.bookstore.common.property.DataSourceProperties;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class DataSourceConfig {
    private final DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        basicDataSource.setUrl(dataSourceProperties.getUrl());
        basicDataSource.setUsername(dataSourceProperties.getUsername());
        basicDataSource.setPassword(dataSourceProperties.getPassword());
        basicDataSource.setInitialSize(dataSourceProperties.getInitialSize());
        basicDataSource.setMaxTotal(dataSourceProperties.getMaxTotal());
        basicDataSource.setMaxIdle(dataSourceProperties.getMaxIdle());
        basicDataSource.setMinIdle(dataSourceProperties.getMinIdle());
        basicDataSource.setMaxWaitMillis(dataSourceProperties.getMaxWaitMillis());
        basicDataSource.setValidationQuery(dataSourceProperties.getValidationQuery());
        basicDataSource.setTestOnBorrow(dataSourceProperties.isTestOnBorrow());
        return basicDataSource;
    }
}

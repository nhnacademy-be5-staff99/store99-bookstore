package com.nhnacademy.store99.bookstore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import com.nhnacademy.store99.bookstore.common.interceptor.AdminCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurer 설정
 *
 * @author seunggyu-kim
 */
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AdminCheckService adminCheckService;
    private final ObjectMapper objectMapper;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new AdminCheckInterceptor(adminCheckService, objectMapper)).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/error/forbidden");
    }
}

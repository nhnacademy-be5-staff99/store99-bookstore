package com.nhnacademy.store99.bookstore.config;

import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import com.nhnacademy.store99.bookstore.common.interceptor.AdminCheckInterceptor;
import com.nhnacademy.store99.bookstore.common.interceptor.XUserIdCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public AdminCheckInterceptor adminCheckInterceptor() {
        return new AdminCheckInterceptor(adminCheckService);
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new XUserIdCheckInterceptor()).excludePathPatterns("/open/**", "/error", "/favicon.ico")
                .order(1);
        registry.addInterceptor(adminCheckInterceptor()).addPathPatterns("/admin/**")
                .order(2);
    }
}

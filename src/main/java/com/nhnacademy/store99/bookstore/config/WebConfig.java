package com.nhnacademy.store99.bookstore.config;

import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import com.nhnacademy.store99.bookstore.common.interceptor.AdminCheckInterceptor;
import com.nhnacademy.store99.bookstore.common.interceptor.XUserIdCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorPathExtension(true)  // 파일 확장자 기반의 콘텐츠 네고시에이션 사용
                .ignoreUnknownPathExtensions(false)  // 알 수 없는 파일 확장자는 거부
                .defaultContentType(MediaType.APPLICATION_JSON)  // 기본 컨텐츠 타입 설정
                .mediaType("svg", MediaType.parseMediaType("image/svg+xml")); // SVG 확장자에 대한 MIME 타입 설정
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://localhost:8090") // 허용할 출처
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP method
                .allowCredentials(true) // 쿠키 인증 요청 허용
                .maxAge(3000); // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
    }
}

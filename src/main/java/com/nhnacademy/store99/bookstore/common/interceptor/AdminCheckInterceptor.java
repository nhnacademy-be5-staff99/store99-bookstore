package com.nhnacademy.store99.bookstore.common.interceptor;

import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AdminCheckInterceptor implements HandlerInterceptor {
    private final AdminCheckService adminCheckService;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception {
        String xUserId = request.getHeader("X-USER-ID");
        if (xUserId.isEmpty()) {
            return false;
        }
        
        return adminCheckService.isAdmin(Long.parseLong(xUserId));
    }
}

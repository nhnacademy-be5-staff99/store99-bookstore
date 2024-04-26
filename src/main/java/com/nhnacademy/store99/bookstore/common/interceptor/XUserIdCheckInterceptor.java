package com.nhnacademy.store99.bookstore.common.interceptor;

import com.nhnacademy.store99.bookstore.common.exception.MissingUserIdHeaderException;
import com.nhnacademy.store99.bookstore.common.thread_local.XUserIdThreadLocal;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class XUserIdCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception {
        String xUserIdHeader = request.getHeader("X-USER-ID");
        if (Objects.isNull(xUserIdHeader)) {
            throw new MissingUserIdHeaderException();
        }
        XUserIdThreadLocal.setXUserId(Long.parseLong(xUserIdHeader));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
                           final ModelAndView modelAndView) throws Exception {
        XUserIdThreadLocal.reset();
    }
}

package com.nhnacademy.store99.bookstore.common.interceptor;

import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import com.nhnacademy.store99.bookstore.common.exception.AdminPermissionDeniedException;
import com.nhnacademy.store99.bookstore.common.thread_local.XUserIdThreadLocal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 관리자 권한을 검사하는 인터셉터
 * <p>/admin/** url로 접근할 경우, X-USER-ID가 있는지, 그리고 해당 ID가 관리자인지 검사한다.</p>
 * <p>관리자가 아닌 경우, 403 Forbidden 상태 코드와 공통 응답 객체를 반환한다.</p>
 *
 * @author seunggyu-kim
 */
@RequiredArgsConstructor
public class AdminCheckInterceptor implements HandlerInterceptor {
    private final AdminCheckService adminCheckService;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception {
        if (Boolean.FALSE.equals(adminCheckService.isAdmin(XUserIdThreadLocal.getXUserId()))) {
            throw new AdminPermissionDeniedException();
        }
        return true;
    }
}

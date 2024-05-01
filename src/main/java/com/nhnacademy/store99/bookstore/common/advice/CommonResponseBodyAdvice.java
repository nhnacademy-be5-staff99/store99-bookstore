package com.nhnacademy.store99.bookstore.common.advice;

import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 공통 응답 처리를 위한 ResponseBodyAdvice
 * <p>
 * Controller에서 ommonResponse를 직접 선언하여 반환하지 않는 경우에, 200 OK로 CommonResponse를 만들어
 * ResponseEntity.ok에 감싸서 반환해준다.
 * </p>
 *
 * @author seunggyu-kim
 */
@RestControllerAdvice
public class CommonResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(final MethodParameter returnType, final Class converterType) {
        return !returnType.getGenericParameterType().getTypeName().contains("CommonResponse");
    }

    @Override
    public CommonResponse<Object> beforeBodyWrite(final Object body, final MethodParameter returnType,
                                                  final MediaType selectedContentType,
                                                  final Class selectedConverterType, final ServerHttpRequest request,
                                                  final ServerHttpResponse response) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int statusCode = servletResponse.getStatus();
        CommonHeader commonHeader =
                CommonHeader.builder().httpStatus(HttpStatus.valueOf(statusCode)).resultMessage("Success").build();
        return CommonResponse.builder().header(commonHeader).result(body).build();
    }
}

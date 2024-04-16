package com.nhnacademy.store99.bookstore.common.advice;

import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

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
        CommonHeader commonHeader = CommonHeader.builder().httpStatus(HttpStatus.OK).resultMessage("Success").build();
        return CommonResponse.builder().header(commonHeader).result(body).build();
    }
}

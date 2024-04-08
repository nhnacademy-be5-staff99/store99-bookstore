package com.nhnacademy.store99.bookstore.common.advice;

import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Common RestControllerAdvice
 * 공통 RestControllerAdvice
 *
 * @author seunggyu-kim
 */
@RestControllerAdvice
public class CommonRestControllerAdvice {

    /**
     * NotFoundException Handler
     *
     * @param ex NotFoundException
     * @return 404 NOT_FOUND
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CommonResponse<String>> notFoundExceptionHandler(NotFoundException ex) {
        CommonHeader commonHeader =
                CommonHeader.builder().httpStatus(HttpStatus.NOT_FOUND).resultMessage(ex.getMessage()).build();
        CommonResponse<String>
                commonResponse = CommonResponse.<String>builder().header(commonHeader).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResponse);
    }
}
package com.nhnacademy.store99.bookstore.common.advice;

import com.nhnacademy.store99.bookstore.common.exception.NotFoundException;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Common RestControllerAdvice
 * 공통 RestControllerAdvice
 *
 * @author seunggyu-kim
 * @author Ahyeon Song
 */
@RestControllerAdvice
public class CommonRestControllerAdvice {

    /**
     * NotFoundException Handler
     * <p>404 NOT_FOUND 에러를 반환할 경우 NotFoundException을 상속받아서 사용
     *
     * @param ex NotFoundException
     * @return 404 NOT_FOUND
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CommonResponse<Void>> notFoundExceptionHandler(NotFoundException ex) {
        CommonHeader commonHeader =
                CommonHeader.builder().httpStatus(HttpStatus.NOT_FOUND).resultMessage(ex.getMessage()).build();
        CommonResponse<Void>
                commonResponse = CommonResponse.<Void>builder().header(commonHeader).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResponse);
    }

    /**
     * 데이터베이스의 무결성 제약 조건 위반 예외 처리
     *
     * @param ex DataIntegrityViolationException
     * @return 500 INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CommonResponse<Void>> integrityConstraintExceptionHandler(
            DataIntegrityViolationException ex) {
        CommonHeader commonHeader =
                CommonHeader.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).resultMessage(ex.getMessage())
                        .build();
        CommonResponse<Void>
                commonResponse = CommonResponse.<Void>builder().header(commonHeader).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commonResponse);
    }


    /**
     * ValidationException Handler
     *
     * @param ex ValidationException
     * @return 400 BAD_REQUEST
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<CommonResponse<Void>> validationExceptionHandler(MethodArgumentNotValidException ex) {
        CommonHeader commonHeader =
                CommonHeader.builder().httpStatus(HttpStatus.BAD_REQUEST).resultMessage(ex.getMessage()).build();
        CommonResponse<Void>
                commonResponse = CommonResponse.<Void>builder().header(commonHeader).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResponse);
    }

}

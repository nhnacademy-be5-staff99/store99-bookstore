package com.nhnacademy.store99.bookstore.tag.controller;

import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.tag.exception.TagAlreadyInUseException;
import com.nhnacademy.store99.bookstore.tag.exception.TagNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class TagControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TagNotFoundException.class)
    @ResponseBody
    public ResponseEntity<CommonResponse<Void>> handletagNotFound(TagNotFoundException ex) {
        CommonHeader header = CommonHeader.builder()
                                                .httpStatus(HttpStatus.NOT_FOUND)
                                                .resultMessage(ex.getMessage())
                                                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CommonResponse<>(header, null));
    }

    @ExceptionHandler(TagAlreadyInUseException.class)
    @ResponseBody
    public ResponseEntity<CommonResponse<Void>> handleTagAlreadyInUse(TagAlreadyInUseException ex) {
        CommonHeader header = CommonHeader.builder()
                .httpStatus(org.springframework.http.HttpStatus.CONFLICT)
                .resultMessage(ex.getMessage())
                .build();
        return ResponseEntity.status(org.springframework.http.HttpStatus.CONFLICT).body(new CommonResponse<>(header, null));
    }
}

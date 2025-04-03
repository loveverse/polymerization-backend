package com.loveverse.fast.common.global;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理业务异常
     */
    //@ExceptionHandler(BusinessException.class)
    //public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
    //    ErrorResponse response = new ErrorResponse(ex.getCode(), ex.getMessage());
    //    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    //}
    //
    ///**
    // * 处理系统异常
    // */
    //@ExceptionHandler(Exception.class)
    //public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    //    ErrorResponse response = new ErrorResponse(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMessage());
    //    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    //}
}

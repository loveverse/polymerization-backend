package com.loveverse.oss.thirdparty.handler;

import com.loveverse.fast.common.http.ResponseCode;
import com.loveverse.fast.common.http.ResponseData;
import com.loveverse.oss.thirdparty.exception.FileException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FileException.class)
    public ResponseData<String> handleFileException(FileException e) {
        return ResponseCode.SYSTEM_ERROR.getResponse(e.getMessage());
    }
}

package com.loveverse.fast.common.exception;

import com.loveverse.fast.common.http.ResponseCode;
import com.loveverse.fast.common.http.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseData<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResponseCode.fromCode(e.getCode()).getResponse(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseData<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResponseCode.SYSTEM_ERROR.getResponse("系统错误");
    }


    //@ExceptionHandler(NotLoginException.class)
    //public BaseResponse<?> notLoginExceptionHandler(RuntimeException e) {
    //    log.error("NotLoginException", e);
    //    return ResultUtils.error(ErrorCodeEnum.NOT_LOGIN_ERROR, "未登录");
    //}
}

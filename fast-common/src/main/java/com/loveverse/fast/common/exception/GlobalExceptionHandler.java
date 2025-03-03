package com.loveverse.fast.common.exception;

import com.loveverse.fast.common.enums.ErrorCodeEnum;
import com.loveverse.fast.common.util.BaseResponse;
import com.loveverse.fast.common.util.ResultUtils;
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
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCodeEnum.SYSTEM_ERROR, "系统错误");
    }


    //@ExceptionHandler(NotLoginException.class)
    //public BaseResponse<?> notLoginExceptionHandler(RuntimeException e) {
    //    log.error("NotLoginException", e);
    //    return ResultUtils.error(ErrorCodeEnum.NOT_LOGIN_ERROR, "未登录");
    //}
}

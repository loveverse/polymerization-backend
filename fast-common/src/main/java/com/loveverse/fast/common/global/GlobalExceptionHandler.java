package com.loveverse.fast.common.global;

import com.loveverse.fast.common.exception.ServiceException;
import com.loveverse.fast.common.http.ResponseCode;
import com.loveverse.fast.common.http.ResponseData;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ControllerAdvice 需要每个方法都加上 @ResponseBody, 否则无法返回json数据
 * 可以使用 @ConfigurationProperties 手动配置环境变量
 */

@Data
@RestControllerAdvice

public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private Environment env;

    /**
     * 处理业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseData<Void> handleServiceException(ServiceException ex, HttpServletRequest request) {
        log.warn("业务异常: {}", request.getRequestURI(), ex);
        ResponseData<Void> responseData = ResponseCode.fromCode(ex.getCode()).getResponse(ex.getMsg());
        if (isDevProfileActive()) {
            responseData.setErrorInfo(ex.getMessage());
        }
        return responseData;
    }

    /**
     * 处理请求方法不支持异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseData<Void> handleRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        log.warn("请求方法不支持: {}", request.getRequestURI(), ex);
        ResponseData<Void> responseData = ResponseCode.METHOD_NOT_ALLOWED.getResponse();
        if (isDevProfileActive()) {
            responseData.setErrorInfo(ex.getMessage());
        }
        return responseData;
    }

    /**
     * 处理权限异常
     */
    //public ResponseData<Void> handlePermissionException(ServiceException serviceException, HttpServletRequest request) {
    //    log.warn("权限异常: {}", request.getRequestURI(), serviceException);
    //    ResponseData<Void> responseData = ResponseCode.FORBIDDEN.getResponse(serviceException.getMessage());
    //    if (isDevProfileActive()) {
    //        responseData.setErrorInfo(serviceException.getMessage());
    //    }
    //    return responseData;
    //}

    /**
     * 处理系统异常,兜底处理所有异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseData<Void> handleException(Throwable ex, HttpServletRequest request) {
        log.error("系统异常: {}", request.getRequestURI(), ex);
        ResponseData<Void> responseData = ResponseCode.SYSTEM_ERROR.getResponse();
        if (isDevProfileActive()) {
            responseData.setErrorInfo(ex.getMessage());
        }
        return responseData;
    }

    /**
     * 检查是否激活了 dev 环境
     */
    private boolean isDevProfileActive() {
        String[] activeProfiles = env.getActiveProfiles();
        for (String profile : activeProfiles) {
            // 根据项目中的配置判断，可以自定义
            if ("dev".equalsIgnoreCase(profile)) {
                return true;
            }
        }
        return false;
    }
}

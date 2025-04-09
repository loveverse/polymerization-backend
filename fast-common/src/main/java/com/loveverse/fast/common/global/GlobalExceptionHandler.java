package com.loveverse.fast.common.global;

import com.loveverse.fast.common.exception.BusinessException;
import com.loveverse.fast.common.http.ResponseCode;
import com.loveverse.fast.common.http.ResponseData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.env.Environment;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ControllerAdvice 需要每个方法都加上 @ResponseBody, 否则无法返回json数据
 * 可以使用 @ConfigurationProperties 手动配置环境变量
 */

@Data
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Environment env;
    private Map<Class<? extends Throwable>, IGlobalExceptionHandler<?>> exceptionHandlers = new HashMap<>();

    /**
     * 异常分为业务异常和服务异常
     * 服务是内部可能会出现的错误捕获
     * 业务是参数等其他情况
     *
     * @param env dev
     */
    public GlobalExceptionHandler(Environment env) {
        this.env = env;
        log.info("✅全局异常初始化成功...");
        registerDefaultHandlers();
    }

    private void registerDefaultHandlers() {
        // 处理业务异常
        registerExceptionHandler(BusinessException.class, (req, ex) -> {
            log.error("参数异常: {}", req.getRequestURI(), ex);
            ResponseData<Void> responseData = ResponseCode.BAD_REQUEST.getResponse();
            if (isDevProfileActive()) {
                responseData.setErrorInfo(ex.getMessage());
            }
            return responseData;
        });

        // 处理系统异常,兜底处理所有异常
        registerExceptionHandler(Exception.class, (req, ex) -> {
            log.error("系统异常: {}", req.getRequestURI(), ex);
            ResponseData<Void> responseData = ResponseCode.SYSTEM_ERROR.getResponse();
            if (isDevProfileActive()) {
                responseData.setErrorInfo(ex.getMessage());
            }
            return responseData;
        });
    }

    public <T extends Throwable> void registerExceptionHandler(Class<T> exceptionType, IGlobalExceptionHandler<T> handler) {
        exceptionHandlers.put(exceptionType, handler);
    }

    @ExceptionHandler(Exception.class)
    public ResponseData<Void> handleException(HttpServletRequest req, Throwable ex) {
        for (Map.Entry<Class<? extends Throwable>, IGlobalExceptionHandler<? extends Throwable>> entry : exceptionHandlers.entrySet()) {
            if (entry.getKey().isInstance(ex)) {
                return handle(req, ex, entry.getValue());
            }
        }
        // 打印异常类型，便于排查
        log.error("未匹配异常类型: {}", ex.getClass().getName());
        return handle(req, ex, exceptionHandlers.get(Exception.class));
    }

    //@SuppressWarnings("unchecked")
    private <T extends Throwable> ResponseData<Void> handle(HttpServletRequest req, Throwable ex, IGlobalExceptionHandler<? extends Throwable> handler) {
        // 这里需要进行类型转换，确保类型安全
        return ((IGlobalExceptionHandler<T>) handler).handle(req, (T) ex);
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

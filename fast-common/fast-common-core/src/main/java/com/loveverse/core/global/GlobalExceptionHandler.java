package com.loveverse.core.global;


import com.loveverse.core.exception.BadRequestException;
import com.loveverse.core.exception.BusinessException;
import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.security.AccessControlException;
import java.util.*;


/**
 * @ControllerAdvice 需要每个方法都加上 @ResponseBody, 否则无法返回json数据
 * 可以使用 @ConfigurationProperties 手动配置环境变量
 */

@Data
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Environment env;

    private Map<Class<? extends Throwable>, IGlobalExceptionHandler<?>> exceptionHandlers = new LinkedHashMap<>();

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
        // 自定义异常注册
        registerDefaultHandlers();
        // SpringMVC 异常
        registerSpringMvcHandlers();
        // SpringSecurity 异常
        registerSpringSecurityHandlers();
    }

    private void registerSpringMvcHandlers() {
        // 处理 SpringMVC 请求参数缺失,例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
        registerExceptionHandler(MissingServletRequestParameterException.class, (req, ex) -> {
            log.warn("请求参数缺失: {}", req.getRequestURI(), ex);
            return ResponseCode.BAD_REQUEST.getResponse(ex.getParameterName());
        });
        // 处理 SpringMVC 请求参数类型错误,例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
        registerExceptionHandler(MethodArgumentTypeMismatchException.class, (req, ex) -> {
            log.warn("请求参数类型错误: {}", req.getRequestURI(), ex);
            return ResponseCode.BAD_REQUEST.getResponse(ex.getMessage());
        });
        // 处理 SpringMVC 参数校验不正确
        registerExceptionHandler(MethodArgumentNotValidException.class, (req, ex) -> {
            log.warn("请求参数不正确: {}", req.getRequestURI(), ex);
            return ResponseCode.BAD_REQUEST.getResponse(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
        });
        // 处理 SpringMVC 参数绑定异常
        registerExceptionHandler(BindException.class, (req, ex) -> {
            log.warn("参数绑定异常: {}", req.getRequestURI(), ex);
            return ResponseCode.BAD_REQUEST.getResponse(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
        });
        registerExceptionHandler(HttpMessageNotReadableException.class, (req, ex) -> {
            log.warn("参数绑定异常: {}", req.getRequestURI(), ex);
            return ResponseCode.BAD_REQUEST.getResponse("");
        });

        registerExceptionHandler(HttpMessageNotReadableException.class, (req, ex) -> {
            log.warn("请求体缺失或格式错误: {}", req.getRequestURI(), ex);
            return ResponseCode.BAD_REQUEST.getResponse("请求体缺失或格式错误");
        });
        // 处理 Validator 校验不通过产生的异常
        registerExceptionHandler(ConstraintViolationException.class, (req, ex) -> {
            log.warn("请求参数不正确: {}", req.getRequestURI(), ex);
            return ResponseCode.BAD_REQUEST.getResponse(ex.getMessage());
        });
        // 处理 SpringMVC 请求地址不存在
        registerExceptionHandler(NoHandlerFoundException.class, (req, ex) -> {
            log.warn("请求地址不存在: {}", req.getRequestURI(), ex);
            return ResponseCode.NOT_FOUND.getResponse(ex.getRequestURL());
        });
        // 处理 SpringMVC 请求方法不正确,例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
        registerExceptionHandler(HttpRequestMethodNotSupportedException.class, (req, ex) -> {
            log.warn("请求方法不正确: {}", req.getRequestURI(), ex);
            return ResponseCode.METHOD_NOT_ALLOWED.getResponse(ex.getMessage());
        });
    }

    private void registerSpringSecurityHandlers(){
        //registerExceptionHandler(AuthenticationEx.class);
    }
    private void registerDefaultHandlers() {
        registerExceptionHandler(BadRequestException.class,(req, ex) -> {
            log.warn("请求异常: {}", req.getRequestURI(), ex);
            return ResponseCode.BAD_REQUEST.getResponse(ex.getMessage());
        });
        // 处理业务异常,所有与业务相关都集成该类
        registerExceptionHandler(BusinessException.class, (req, ex) -> {
            log.warn("业务异常: {}", req.getRequestURI(), ex);
            return ResponseCode.BAD_REQUEST.getResponse(ex.getMessage());
        });
        // 处理系统异常,兜底处理所有异常,消息使用枚举默认
        registerExceptionHandler(Exception.class, (req, ex) -> {
            log.error("服务器异常: {}", req.getRequestURI(), ex);
            return ResponseCode.SYSTEM_ERROR.getResponse();
        });
        // 通常不会走到这,只有 Error 或者 Throwable 及其子类才会执行(OutOfMemoryError)
        registerExceptionHandler(Throwable.class, (req, ex) -> {
            log.error("系统严重错误: {}", req.getRequestURI(), ex);
            return ResponseCode.UNKNOWN.getResponse("系统严重错误");
        });
    }

    public <T extends Throwable> void registerExceptionHandler(Class<T> exceptionType, IGlobalExceptionHandler<T> handler) {
        exceptionHandlers.put(exceptionType, handler);
    }

    @ExceptionHandler(Exception.class)
    public ResponseData<Void> handleException(HttpServletRequest req, Throwable ex) {
        // 1. 优先检查精确匹配的处理器
        Throwable rootCause = getRootCause(ex);
        // 2. 优先匹配 rootCause
        IGlobalExceptionHandler<?> handler = findBestMatchHandler(rootCause);
        if (handler != null) {
            return handle(req, rootCause, handler);
        }
        // 3. 如果 rootCause 未匹配，再尝试原始异常
        if (rootCause != ex) {
            handler = findBestMatchHandler(ex);
            if (handler != null) {
                return handle(req, ex, handler);
            }
        }
        return handle(req, ex, exceptionHandlers.get(Exception.class));
    }

    private Throwable getRootCause(Throwable ex) {
        Set<Throwable> seen = new HashSet<>();
        while (ex.getCause() != null && !seen.contains(ex.getCause())) {
            seen.add(ex.getCause());
            ex = ex.getCause();
        }
        return ex;
    }

    //@SuppressWarnings("unchecked")
    private <T extends Throwable> ResponseData<Void> handle(HttpServletRequest req, T ex, IGlobalExceptionHandler<? extends Throwable> handler) {
        // 这里需要进行类型转换，确保类型安全
        ResponseData<Void> handle = ((IGlobalExceptionHandler<T>) handler).handle(req, ex);
        boolean isActiveDev = Arrays.asList(env.getActiveProfiles()).contains("dev");
        if (isActiveDev) {
            handle.setErrorInfo(collectErrorInfo(ex));
        }
        return handle;
    }


    private IGlobalExceptionHandler<?> findBestMatchHandler(Throwable ex) {
        // 1. 精确匹配
        IGlobalExceptionHandler<?> handler = exceptionHandlers.get(ex.getClass());
        if (handler != null) {
            return handler;
        }
        // 2. 查找所有可能的父类/接口匹配
        List<Class<? extends Throwable>> candidateClasses = new ArrayList<>();
        for (Class<? extends Throwable> cls : exceptionHandlers.keySet()) {
            if (cls.isInstance(ex)) {
                candidateClasses.add(cls);
            }
        }

        // 3. 按继承深度排序（子类优先）
        candidateClasses.sort((c1, c2) -> {
            if (c1.isAssignableFrom(c2)) return 1;
            if (c2.isAssignableFrom(c1)) return -1;
            return 0;
        });

        // 4. 返回最匹配的处理器
        return candidateClasses.isEmpty() ? null : exceptionHandlers.get(candidateClasses.get(0));
    }

    private List<String> collectErrorInfo(Throwable e) {
        List<String> errorInfos = new ArrayList<>();
        errorInfos.add(e.getClass().getName() + ": " + e.getMessage());

        StackTraceElement[] stackTrace = e.getStackTrace();
        if (stackTrace.length == 0) return errorInfos;

        String rootPackage = getRootPackageName(stackTrace[0].getClassName());
        if (rootPackage.isEmpty()) return errorInfos;

        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().startsWith(rootPackage)) {
                errorInfos.add(element.toString());
            }
        }
        return errorInfos;
    }

    private String getRootPackageName(String className) {
        int secondDotIndex = className.indexOf('.', className.indexOf('.') + 1);
        return secondDotIndex == -1 ? className : className.substring(0, secondDotIndex);
    }
}

package com.loveverse.fast.common.exception;


import com.loveverse.fast.common.http.ResponseCode;

/**
 * 抛异常工具类

 */
public class ThrowUtils {

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param errorCode
     */
    public static void throwIf(boolean condition, ResponseCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param errorCode
     * @param msg
     */
    public static void throwIf(boolean condition, ResponseCode errorCode, String msg) {
        throwIf(condition, new BusinessException(errorCode, msg));
    }
}

package com.loveverse.fast.common.util;

import com.loveverse.fast.common.enums.ErrorCodeEnum;

/**
 * 返回工具类
 *
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data a
     * @param <T> a
     * @return a
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode a
     * @return a
     */
    public static BaseResponse<Void> error(ErrorCodeEnum errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @return
     */
    public static BaseResponse<Void> error(int code, String message) {
        return new BaseResponse<>(code, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse<Void> error(ErrorCodeEnum errorCode, String message) {
        return new BaseResponse<>(errorCode.getCode(), null, message);
    }
}

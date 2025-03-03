package com.loveverse.fast.common.util;

import com.loveverse.fast.common.enums.ErrorCodeEnum;

/**
 * 返回工具类
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data []
     * @param <T>  List
     * @return List
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode 500
     * @return null
     */
    public static <T> BaseResponse<T> error(ErrorCodeEnum errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code 500
     * @param msg  失败
     * @return null
     */
    public static <T> BaseResponse<T> error(int code, String msg) {
        return new BaseResponse<>(code, null, msg);
    }

    /**
     * 失败
     *
     * @param errorCode 500
     * @return null
     */
    public static <T> BaseResponse<T> error(ErrorCodeEnum errorCode, String msg) {
        return new BaseResponse<>(errorCode.getCode(), null, msg);
    }
}

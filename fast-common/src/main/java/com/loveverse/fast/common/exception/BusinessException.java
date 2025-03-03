package com.loveverse.fast.common.exception;


import com.loveverse.fast.common.enums.ErrorCodeEnum;

/**
 * 自定义异常类
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(ErrorCodeEnum errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCodeEnum errorCode, String msg) {
        super(msg);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}

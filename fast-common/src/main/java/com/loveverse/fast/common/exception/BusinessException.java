package com.loveverse.fast.common.exception;


import com.loveverse.fast.common.http.ResponseCode;
import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(ResponseCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    public BusinessException(ResponseCode errorCode, String msg) {
        super(msg);
        this.code = errorCode.getCode();
    }
}

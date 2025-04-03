package com.loveverse.fast.common.exception;

import com.loveverse.fast.common.http.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author love
 * @since 2025/4/3
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {

    private int code;

    private String msg;

    public ServiceException(ResponseCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ServiceException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public ServiceException setCode(int code) {
        this.code = code;
        return this;
    }

    public ServiceException setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}

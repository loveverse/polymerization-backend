package com.loveverse.fast.common.util;

import com.loveverse.fast.common.enums.ErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 */
@Data
public class BaseResponse<T> implements Serializable {

        private int code;

        private T data;

        private String msg;

        public BaseResponse(int code, T data, String msg) {
            this.code = code;
            this.data = data;
            this.msg = msg;
        }

        public BaseResponse(int code, T data) {
            this(code, data, "");
        }

        public BaseResponse(ErrorCodeEnum errorCode) {
            this(errorCode.getCode(), null, errorCode.getMsg());
        }


}

package com.loveverse.fast.common.enums;


import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    SUCCESS(200, "请求成功"),
    PARAMS_ERROR(400, "请求参数错误"),
    NOT_LOGIN_ERROR(401, "未登录"),
    //NO_AUTH_ERROR(403, "无权限"),
    FORBIDDEN_ERROR(403, "禁止访问"),
    NOT_FOUND_ERROR(404, "请求数据不存在"),
    SYSTEM_ERROR(500, "系统内部异常"),
    OPERATION_ERROR(501, "操作失败");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

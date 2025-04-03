package com.loveverse.fast.common.http;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor        // 给final类型的生成构造函数
public enum ResponseCode {
    SUCCESS(200, "操作成功"),

    // ================================= 业务错误码 ==================================
    BAD_REQUEST(400, "请求参数错误"),
    NOT_LOGIN(401, "账号未登录"),
    FORBIDDEN(403, "无权限"),
    NOT_FOUND(404, "请求未找到"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    LOCKED(423, "资源被锁定"),
    TOO_MANY_REQUESTS(429, "请求过于频繁"),

    // ================================ 系统错误码 ==================================
    SYSTEM_ERROR(500, "服务器内部异常"),
    NOT_IMPLEMENTED(501, "功能未实现"),

    // ================================ 未知错误码 ==================================
    UNKNOWN(999, "未知错误");
    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String msg;

    /**
     * 通过code查询错误信息
     *
     * @param code 200
     * @return 请求成功
     */
    public static ResponseCode fromCode(int code) {
        for (ResponseCode responseCode : ResponseCode.values()) {
            if (responseCode.getCode() == code) {
                return responseCode;
            }
        }
        return SYSTEM_ERROR; // 也可以返回 null，根据业务需求调整
    }

    public <T> ResponseData<T> getResponse(String msg, T data) {
        return new ResponseData<>(msg, this.code, data);
    }

    public <T> ResponseData<T> getResponse(T data) {
        return this.getResponse(this.msg, data);
    }

    public <T> ResponseData<T> getResponse(String msg) {
        return this.getResponse(msg, null);
    }

    public <T> ResponseData<T> getResponse() {
        return this.getResponse(this.msg, null);
    }
}

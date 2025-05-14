package com.loveverse.core.http;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "响应信息")
public class ResponseData<T> {
    @Schema(description = "状态码",requiredMode = Schema.RequiredMode.REQUIRED)
    private int code;
    @Schema(description = "数据",requiredMode = Schema.RequiredMode.REQUIRED)
    private T data;
    @Schema(description = "消息",requiredMode = Schema.RequiredMode.REQUIRED)
    private String msg;
    @Schema(description = "错误信息",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> errorInfo;


    public ResponseData(String msg, int code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }
}

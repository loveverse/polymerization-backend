package com.loveverse.fast.common.http;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "响应信息")
public class ResponseData<T> {
    @ApiModelProperty(value = "状态码", required = true)
    private int code;
    @ApiModelProperty(value = "数据", required = true)
    private T data;
    @ApiModelProperty(value = "消息", required = true)
    private String msg;

    public ResponseData(String msg, int code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }
}

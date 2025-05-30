package com.loveverse.auth.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author love
 * @since 2025/4/28
 */
@Data
@Schema(description = "登录返回信息")
public class UserLoginVO {
    @Schema(description = "用户id", example = "1")
    private Long userId;

    @Schema(description = "访问令牌,需要在请求头添加 Authorization：${token}", example = "a")
    private String token;

    @Schema(description = "令牌前缀", example = "Bearer")
    private String tokenPrefix;

    @Schema(description = "过期时间", example = "86400")
    private LocalDateTime expireTime;

}

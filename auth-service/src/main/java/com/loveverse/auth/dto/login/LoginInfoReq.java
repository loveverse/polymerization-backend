package com.loveverse.auth.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author love
 * @since 2025/4/28
 */
@Data
@Schema(description = "登录参数")
public class LoginInfoReq {
    @Schema(description = "用户名", example = "admin")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码", example = "admin")
    @NotBlank(message = "密码不能为空")
    private String password;
}

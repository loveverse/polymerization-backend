package com.loveverse.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author love
 * @since 2025/4/28
 */
@Data
@Schema(description = "登录参数")
public class UserLoginDTO {
    @Schema(description = "用户名", example = "admin")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码", example = "admin")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(description = "验证码", example = "1234")
    @NotBlank(message = "验证码不能为空", groups = CaptchaEnableGroup.class)
    private String captchaCode;

    @Schema(description = "uuid", example = "")
    @NotBlank(message = "uuid不能为空", groups = CaptchaEnableGroup.class)
    private String captchaKey;

    // 开启验证码校验的分组
    public interface CaptchaEnableGroup {

    }
}

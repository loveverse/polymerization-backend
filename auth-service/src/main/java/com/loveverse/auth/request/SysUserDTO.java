package com.loveverse.auth.request;

import com.loveverse.core.valid.ValidGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author love
 * @since 2025/5/19 18:04
 */
@Data
@Schema(description = "用户信息")
public class SysUserDTO {
    @Null(groups = ValidGroup.Create.class)
    @NotNull(groups = ValidGroup.Update.class)
    @Schema(description = "用户id", example = "1")
    private Long id;

    @NotBlank(message = "用户名不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "用户名", maxLength = 64, example = "admin")
    private String username;

    @Schema(description = "昵称", maxLength = 64)
    private String nickname;

    @NotBlank(message = "密码不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "密码", maxLength = 64)
    private String password;

    @Schema(description = "性别：U-未知，M-男，W-女", maxLength = 1, example = "U")
    private String sex;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "状态：0-停用，1-正常", maxLength = 1, example = "1")
    private Integer status;

    @Schema(description = "手机号", maxLength = 32)
    private String phoneNumber;

    @Schema(description = "邮箱", maxLength = 64)
    private String email;

    @Schema(description = "角色ids")
    private List<Long> roleIds;

}

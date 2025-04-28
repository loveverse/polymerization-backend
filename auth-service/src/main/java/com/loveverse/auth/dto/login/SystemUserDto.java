package com.loveverse.auth.dto.login;

import com.loveverse.mybatis.dto.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author love
 * @since 2025/4/28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户信息")
public class SystemUserDto extends BaseDto {

    @Schema(description = "用户ID", example = "1")
    private Long id;

    @Schema(description = "用户名", example = "username")
    private String userName;

    @Schema(description = "昵称", example = "nickname")
    private String nickName;

    @Schema(description = "性别：U-未知，M-男，W-女", example = "M")
    private String sex;

    @Schema(description = "状态：0-停用，1-正常", example = "1")
    private String status;

    @Schema(description = "手机号", example = "1234567890")
    private String phoneNumber;

    @Schema(description = "邮箱", example = "user@example.com")
    private String email;

    @Schema(description = "用户类型：0-管理员，1-普通用户", example = "1")
    private String userType;
}

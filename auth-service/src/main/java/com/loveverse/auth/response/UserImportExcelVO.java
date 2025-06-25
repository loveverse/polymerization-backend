package com.loveverse.auth.response;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author love
 * @since 2025/6/18 9:50
 */
@Data
@Schema(description = "用户信息")
public class UserImportExcelVO {
    @Schema(description = "用户名")
    private String username;

    @ExcelProperty
    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "性别：U-未知，M-男，W-女")
    private String gender;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "状态：0-停用，1-正常")
    private Integer status;

    @Schema(description = "手机号")
    private String phoneNumber;

    @Schema(description = "邮箱")
    private String email;
}

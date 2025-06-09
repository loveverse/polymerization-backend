package com.loveverse.auth.response;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.loveverse.mybatis.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * @author love
 * @since 2025/4/28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户信息")
public class SysUserVO extends BaseVO {

    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "性别：U-未知，M-男，W-女")
    private String sex;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "状态：0-停用，1-正常")
    private Integer status;

    @Schema(description = "手机号")
    private String phoneNumber;

    @Schema(description = "邮箱")
    private String email;


    @Schema(description = "角色信息")
    private List<SysRoleVO> roleList;
}

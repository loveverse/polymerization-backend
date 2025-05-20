package com.loveverse.auth.response;

import com.loveverse.auth.entity.SysMenu;
import com.loveverse.auth.entity.SysRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author love
 * @since 2025/4/28
 */
@Data
@Schema(description = "登录返回信息")
public class UserLoginVO {

    private String token;

    @Schema(description = "用户信息")
    private SysUserVO user;

    @Schema(description = "用户角色信息列表")
    private List<SysRole> roles;

    @Schema(description = "权限菜单列表")
    private List<SysMenu> menus;
}

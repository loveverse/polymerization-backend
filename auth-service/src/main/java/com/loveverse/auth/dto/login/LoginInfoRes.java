package com.loveverse.auth.dto.login;

import com.loveverse.auth.entity.SystemMenu;
import com.loveverse.auth.entity.SystemRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author love
 * @since 2025/4/28
 */
@Data
@Schema(description = "登录返回信息")
public class LoginInfoRes {

    private String token;

    @Schema(description = "用户信息")
    private SystemUserDto user;

    @Schema(description = "用户角色信息列表")
    private List<SystemRole> roles;

    @Schema(description = "权限菜单列表")
    private List<SystemMenu> menus;
}

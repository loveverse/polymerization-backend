package com.loveverse.auth.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author love
 * @since 2025/6/10 14:35
 */
@Data
@Schema(description = "用户权限信息")
public class UserAuthorityInfoVO {

    @Schema(description = "用户菜单信息")
    private List<SysMenuVO> menus;

    @Schema(description = "权限标识集合")
    private Set<String> permissions;

    @Schema(description = "角色集合")
    private Set<String> roles;
}

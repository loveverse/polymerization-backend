package com.loveverse.auth.request;

import com.loveverse.core.valid.ValidGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author love
 * @since 2025/6/4 18:21
 */
@Data
@Schema(description = "菜单信息")
public class SysMenuDTO {

    @Null(groups = ValidGroup.Create.class)
    @NotNull(groups = ValidGroup.Update.class)
    @Schema(description = "id")
    private Long id;

    @NotNull(groups = {ValidGroup.Create.class, ValidGroup.Update.class})
    @Schema(description = "模块id")
    private Long moduleId;

    @NotBlank(message = "菜单名称不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "菜单名称", maxLength = 32)
    private String menuName;

    @NotBlank(message = "路由路径不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "路由路径（全路径）", maxLength = 128)
    private String path;

    @NotBlank(message = "菜单类型不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "菜单类型：0-目录，1-菜单，2-按钮", maxLength = 30, example = "0")
    private String menuType;

    @Schema(description = "父菜单ID，设置默认值，防止创建没传入值", example = "0")
    private Long parentId;

    @Schema(description = "菜单图标", maxLength = 64)
    private String icon;

    @Schema(description = "权限标识")
    private String permission;

    @Schema(description = "是否可见：0-隐藏，1-可见", maxLength = 1, example = "1")
    private Integer visible;

    @Schema(description = "排序值", example = "0")
    private Integer sortOrder;
}
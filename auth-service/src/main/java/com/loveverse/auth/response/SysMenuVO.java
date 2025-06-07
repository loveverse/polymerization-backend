package com.loveverse.auth.response;

import com.loveverse.mybatis.vo.BaseTimeVO;
import com.loveverse.mybatis.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author love
 * @since 2025/6/4 18:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "菜单信息")
public class SysMenuVO extends BaseTimeVO {
    @Schema(description = "id")
    private Long id;

    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "路由路径（全路径）")
    private String path;

    @Schema(description = "菜单类型：0-目录，1-菜单，2-按钮", example = "0")
    private String menuType;

    @Schema(description = "父菜单ID，设置默认值，防止创建没传入值", example = "0")
    private Long parentId;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "权限标识")
    private String permission;

    @Schema(description = "是否可见：0-隐藏，1-可见", example = "1")
    private Integer visible;

    @Schema(description = "排序值", example = "0")
    private Integer sortOrder;

    private List<SysMenuVO> children;
}

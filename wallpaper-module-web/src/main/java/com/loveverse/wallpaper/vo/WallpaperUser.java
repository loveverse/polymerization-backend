package com.loveverse.wallpaper.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
/**
 * <p>
 * 用户表
 * </p>
 *
 * @author loveverse
 * @since 2025-02-28
 */
@Data
@TableName("wallpaper_user")
@ApiModel(value = "WallpaperUser对象", description = "用户表")
public class WallpaperUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String userAvatar;

    /**
     * 角色保留字段
     */
    @ApiModelProperty("角色保留字段")
    private String userRole;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除[1:有效,null:删除]
     */
    @ApiModelProperty("逻辑删除[1:有效,null:删除]")
    private Integer valid;
}

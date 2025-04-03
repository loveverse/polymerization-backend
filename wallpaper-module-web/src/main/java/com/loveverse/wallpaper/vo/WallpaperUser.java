package com.loveverse.wallpaper.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "WallpaperUser对象")
public class WallpaperUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @Schema(description ="用户名")
    private String username;

    /**
     * 密码
     */
    @Schema(description ="密码")
    private String password;

    /**
     * 用户头像
     */
    @Schema(description ="用户头像")
    private String userAvatar;

    /**
     * 角色保留字段
     */
    @Schema(description ="角色保留字段")
    private String userRole;

    /**
     * id
     */
    @Schema(description ="id")
    private Long id;

    /**
     * 创建时间
     */
    @Schema(description ="创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description ="更新时间")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除[1:有效,null:删除]
     */
    @Schema(description ="逻辑删除[1:有效,null:删除]")
    private Integer valid;
}

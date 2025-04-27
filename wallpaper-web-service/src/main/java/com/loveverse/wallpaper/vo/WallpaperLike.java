package com.loveverse.wallpaper.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 喜欢记录表
 * </p>
 *
 * @author loveverse
 * @since 2025-02-28
 */
@Data
@Schema(description = "WallpaperLike对象")
public class WallpaperLike implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 图片id
     */
    @Schema(description = "图片id")
    private Long pictureId;

    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除[1:有效,null:删除]
     */
    @Schema(description = "逻辑删除[1:有效,null:删除]")
    private Integer valid;
}

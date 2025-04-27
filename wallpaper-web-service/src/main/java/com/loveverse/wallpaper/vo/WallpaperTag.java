package com.loveverse.wallpaper.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 标签表
 * </p>
 *
 * @author loveverse
 * @since 2025-02-28
 */
@Data
@TableName("wallpaper_tag")
@Schema(description = "WallpaperTag对象")
public class WallpaperTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签名称
     */
    @Schema(description ="标签名称")
    private String tagName;

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

package com.loveverse.wallpaper.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 图片表
 * </p>
 *
 * @author loveverse
 * @since 2025-02-28
 */
@Data
@TableName("wallpaper_picture")
@Schema(description = "WallpaperPicture对象")
public class WallpaperPicture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片url地址
     */
    @Schema(description = "图片url地址")
    private String url;

    /**
     * 图片缩略图
     */
    @Schema(description = "图片缩略图")
    private String thumbnailUrl;

    /**
     * 图片名称
     */
    @Schema(description = "图片名称")
    private String name;

    /**
     * 标签
     */
    @Schema(description = "标签")
    private String tags;

    /**
     * 图片大小
     */
    @Schema(description = "图片大小")
    private Long picSize;

    /**
     * 图片宽度
     */
    @Schema(description = "图片宽度")
    private Integer picWidth;

    /**
     * 图片高度
     */
    @Schema(description = "图片高度")
    private Integer picHeight;

    /**
     * 图片比例
     */
    @Schema(description = "图片比例")
    private Double picScale;

    /**
     * 图片主色调
     */
    @Schema(description = "图片主色调")
    private String picColor;

    /**
     * 喜欢次数
     */
    @Schema(description = "喜欢次数")
    private Integer likeCount;

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

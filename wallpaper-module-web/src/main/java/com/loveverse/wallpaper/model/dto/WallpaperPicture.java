package com.loveverse.wallpaper.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@ApiModel(value = "WallpaperPicture对象", description = "图片表")
public class WallpaperPicture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片url地址
     */
    @ApiModelProperty("图片url地址")
    private String url;

    /**
     * 图片缩略图
     */
    @ApiModelProperty("图片缩略图")
    private String thumbnailUrl;

    /**
     * 图片名称
     */
    @ApiModelProperty("图片名称")
    private String name;

    /**
     * 标签
     */
    @ApiModelProperty("标签")
    private String tags;

    /**
     * 图片大小
     */
    @ApiModelProperty("图片大小")
    private Long picSize;

    /**
     * 图片宽度
     */
    @ApiModelProperty("图片宽度")
    private Integer picWidth;

    /**
     * 图片高度
     */
    @ApiModelProperty("图片高度")
    private Integer picHeight;

    /**
     * 图片比例
     */
    @ApiModelProperty("图片比例")
    private Double picScale;

    /**
     * 图片主色调
     */
    @ApiModelProperty("图片主色调")
    private String picColor;

    /**
     * 喜欢次数
     */
    @ApiModelProperty("喜欢次数")
    private Integer likeCount;

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

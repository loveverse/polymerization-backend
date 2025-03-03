package com.loveverse.wallpaper.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@ApiModel(value = "WallpaperTag对象", description = "标签表")
public class WallpaperTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签名称
     */
    @ApiModelProperty("标签名称")
    private String tagName;

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

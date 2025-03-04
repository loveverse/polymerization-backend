package com.loveverse.wallpaper.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import com.loveverse.fast.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@TableCharset(MySqlCharsetConstant.UTF8MB4)
@TableEngine(MySqlEngineConstant.InnoDB)
@Table(name = "wallpaper_picture", comment = "图片表")
public class Picture extends BaseEntity {
    @Column(comment = "图片url地址", isNull = false)
    @Unique(columns = {"url"})
    private String url;

    @Column(comment = "图片缩略图", isNull = false)
    private String thumbnailUrl;

    @Column(comment = "图片名称", isNull = false)
    private String name;

    @Column(comment = "标签")
    private String tags;

    @Column(comment = "图片大小", isNull = false)
    private Long picSize;

    @Column(comment = "图片宽度", isNull = false)
    private Integer picWidth;

    @Column(comment = "图片高度", isNull = false)
    private Integer picHeight;

    @Column(comment = "图片比例", isNull = false)
    private Double picScale;

    @Column(comment = "图片主色调", isNull = false)
    private String picColor;

    @Column(comment = "喜欢次数")
    private Integer likeCount;

    @Column(comment = "图片来源")
    private String origin;
}

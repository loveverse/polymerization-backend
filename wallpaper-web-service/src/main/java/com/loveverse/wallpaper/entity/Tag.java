package com.loveverse.wallpaper.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableCharset;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableEngine;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;

import com.loveverse.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@TableCharset(MySqlCharsetConstant.UTF8MB4)
@TableEngine(MySqlEngineConstant.InnoDB)
@Table(name = "wallpaper_tag", comment = "标签表")
public class Tag extends BaseEntity {
    @Column(comment = "标签名称", isNull = false)
    private String tagName;
}

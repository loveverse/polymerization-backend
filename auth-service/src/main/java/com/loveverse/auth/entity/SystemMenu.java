package com.loveverse.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

import java.io.Serializable;

/**
 * @author love
 * @since 2025/4/23
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@Table(name = "sys_menu", comment = "菜单权限表")
public class SystemMenu extends BaseEntity {
    @TableId(type = IdType.AUTO)
    @Column(isAutoIncrement = true, isKey = true, comment = "id")
    private Long id;

    @Column(comment = "菜单名称", length = 32, isNull = false)
    private String menuName;

    @Column(comment = "路由路径（全路径）", length = 128, isNull = false)
    private String path;

    @Column(comment = "菜单类型：0-目录，1-菜单，2-按钮", length = 30, isNull = false, defaultValue = "0")
    private String menuType;

    @Column(comment = "父菜单ID，设置默认值，防止创建没传入值", isNull = false, defaultValue = "0")
    private Long parentId;

    @Column(comment = "菜单图标", length = 64)
    private String icon;

    @Column(comment = "权限标识")
    private String permission;

    @Column(comment = "是否可见：0-隐藏，1-可见", length = 1, isNull = false, defaultValue = "1")
    private Integer visible;

    @Column(comment = "排序值", isNull = false, defaultValue = "0")
    private Integer sortOrder;


}

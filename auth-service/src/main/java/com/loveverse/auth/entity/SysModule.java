package com.loveverse.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.loveverse.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author love
 * @since 2025/5/23 14:58
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_module")
@Table(value = "sys_module", comment = "模块表")
public class SysModule extends BaseEntity {
    @TableId(type = IdType.AUTO)
    @Column(comment = "id", isAutoIncrement = true, isKey = true)
    private Long id;

    @Column(comment = "模块名称", isNull = false, length = 100)
    private String moduleName;

    @Column(comment = "模块key", isNull = false, length = 100)
    @Unique(value = "module_value", columns = {"valid", "module_value"})
    private String moduleValue;

    @Column(comment = "模块图标")
    private String moduleIcon;

    @Column(comment = "模块配置")
    private String moduleConfig;

    @Column(comment = "排序值", defaultValue = "0")
    private Integer sortOrder;

}

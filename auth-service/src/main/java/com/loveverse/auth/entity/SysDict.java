package com.loveverse.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.loveverse.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author love
 * @since 2025/4/23
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict")
@Table(value = "sys_dict", comment = "字典表")
public class SysDict extends BaseEntity {
    @TableId(type = IdType.AUTO)
    @Column(comment = "id", isAutoIncrement = true, isKey = true)
    private Long id;

    @Column(comment = "字典名（英文）", length = 100,isNull = false)
    private String dictValue;

    @Column(comment = "字典名（中文名）", length = 100,isNull = false)
    private String dictLabel;

    @Column(comment = "字典类型分组,null为通用", length = 100)
    private String dictType;
}

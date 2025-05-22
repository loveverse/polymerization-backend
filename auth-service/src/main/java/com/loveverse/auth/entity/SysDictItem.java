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
@TableName("sys_dict_item")
@Table(value = "sys_dict_item", comment = "字典详情表")
public class SysDictItem extends BaseEntity {
    @TableId(type = IdType.AUTO)
    @Column(comment = "id", isAutoIncrement = true, isKey = true)
    private Long id;

    @Column(comment = "字典id", isNull = false)
    private Long dictId;

    @Column(comment = "字典详情值", length = 100, isNull = false)
    private String dictItemValue;

    @Column(comment = "字典详情名称", length = 100, isNull = false)
    private String dictItemLabel;

    @Column(comment = "排序值", isNull = false, defaultValue = "0")
    private Integer sortOrder;
}

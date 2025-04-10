package com.loveverse.fast.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableCharset;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableEngine;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@TableCharset(MySqlCharsetConstant.UTF8MB4)
@TableEngine(MySqlEngineConstant.InnoDB)
@Data
public class BaseTime extends BaseId {
    @TableField(fill = FieldFill.INSERT)
    @Column(comment = "创建时间", type = MySqlTypeConstant.DATETIME,isNull = false)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Column(comment = "更新时间", type = MySqlTypeConstant.DATETIME,isNull = false)
    private LocalDateTime updateTime;
}

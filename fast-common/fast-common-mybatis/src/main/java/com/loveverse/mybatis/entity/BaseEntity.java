package com.loveverse.mybatis.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableCharset;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableEngine;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@TableEngine(MySqlEngineConstant.InnoDB)
@TableCharset(MySqlCharsetConstant.UTF8MB4)
public class BaseEntity implements Serializable {
    // 父类不需要重复实现 Serializable
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    @Column(comment = "id", isKey = true)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    @Column(comment = "创建时间", type = MySqlTypeConstant.DATETIME,isNull = false)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Column(comment = "更新时间", type = MySqlTypeConstant.DATETIME,isNull = false)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    @Column(comment = "逻辑删除[1:有效,null:删除]", defaultValue = "1")
    private Integer valid;

    @TableField(fill = FieldFill.INSERT)
    @Version
    @Column(comment = "乐观锁版本号", isNull = false)
    private Long version;

}

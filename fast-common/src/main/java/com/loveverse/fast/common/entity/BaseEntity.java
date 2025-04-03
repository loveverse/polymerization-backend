package com.loveverse.fast.common.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableCharset;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableEngine;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@TableCharset(MySqlCharsetConstant.UTF8MB4)
@TableEngine(MySqlEngineConstant.InnoDB)
@Data
public class BaseEntity extends BaseTime {

    @TableLogic
    @Column(comment = "逻辑删除[1:有效,null:删除]", defaultValue = "1")
    private Integer valid;

    @Version
    @Column(comment = "乐观锁版本号", isNull = false)
    private Long version;

}

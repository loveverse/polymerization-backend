package com.loveverse.mybatis.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author love
 * @since 2025/4/28
 */
@Data
public class BaseTimeDto implements Serializable {
    // 父类不需要重复实现 Serializable
    private static final long serialVersionUID = 1L;

    private Long id;
    // 创建时间
    private LocalDateTime createTime;
    // 修改时间
    private LocalDateTime updateTime;
}

package com.loveverse.mybatis.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author love
 * @since 2025/4/28
 */
@Data
public class BaseTimeVO implements Serializable {
    // 父类不需要重复实现 Serializable
    private static final long serialVersionUID = 1L;

    private Long id;
    // 创建时间
    private LocalDateTime createTime;
    // 修改时间
    private LocalDateTime updateTime;
}

package com.loveverse.mybatis.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class BaseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @JsonIgnore
    private Integer valid;

    @JsonIgnore
    private Long version;
}

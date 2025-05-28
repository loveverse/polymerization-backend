package com.loveverse.mybatis.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author love
 * @since 2025/4/28
 */
@Data
public class BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime createTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updateTime;

    @JsonIgnore
    private Integer valid;

    @JsonIgnore
    private Long version;
}

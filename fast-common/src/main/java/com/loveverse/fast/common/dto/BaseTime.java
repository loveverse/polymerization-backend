package com.loveverse.fast.common.dto;

import com.loveverse.fast.common.valid.IValidGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseTime extends BaseId {
    @NotNull(groups = {IValidGroup.CreateTime.class})
    private LocalDateTime createTime;

    @NotNull(groups = {IValidGroup.UpdateTime.class})
    private LocalDateTime updateTime;
}

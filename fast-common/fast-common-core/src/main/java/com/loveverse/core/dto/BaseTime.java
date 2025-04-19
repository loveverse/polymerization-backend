package com.loveverse.core.dto;


import com.loveverse.core.valid.IValidGroup;
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

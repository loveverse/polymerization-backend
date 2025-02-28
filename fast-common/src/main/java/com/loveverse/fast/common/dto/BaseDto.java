package com.loveverse.fast.common.dto;

import com.loveverse.fast.common.valid.IValidGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseDto extends BaseTime {

    @NotNull(groups = {IValidGroup.Valid.class})
    private Integer valid;
}

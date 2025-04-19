package com.loveverse.core.dto;


import com.loveverse.core.valid.IValidGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseDto extends BaseTime {
    @NotNull(groups = {IValidGroup.Valid.class})
    private Integer valid;

    @NotNull(groups = {IValidGroup.Version.class})
    private Long version;
}

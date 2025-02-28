package com.loveverse.fast.common.dto;

import com.loveverse.fast.common.valid.IValidGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BaseId {
    @NotNull(groups = {IValidGroup.Id.class})
    private Long id;
}

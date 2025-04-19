package com.loveverse.core.dto;


import com.loveverse.core.valid.IValidGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BaseId {
    @NotNull(groups = {IValidGroup.Id.class})
    private Long id;
}

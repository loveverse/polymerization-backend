package com.loveverse.auth.request;

import com.loveverse.core.valid.ValidGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author love
 * @since 2025/5/22 16:34
 */
@Data
@Schema(description = "字典信息")
public class SysDictDTO {

        @Null(groups = ValidGroup.Create.class)
        @NotNull(groups = ValidGroup.Update.class)
    @Schema(description = "字典id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1")
    private Long id;

    @NotBlank(message = "字典名（英文）不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "字典名（英文）", maxLength = 100, example = "sex_type")
    private String dictValue;

    @NotBlank(message = "字典名（中文）不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "字典名（中文名）", maxLength = 100, example = "性别")
    private String dictLabel;

    @Schema(description = "字典类型分组,null为通用", requiredMode = Schema.RequiredMode.NOT_REQUIRED, maxLength = 100)
    private String dictType;

}

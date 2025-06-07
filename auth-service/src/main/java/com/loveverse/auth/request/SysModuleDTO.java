package com.loveverse.auth.request;

import com.loveverse.core.valid.ValidGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author love
 * @since 2025/5/23 15:40
 */
@Data
@Schema(description = "模块信息")
public class SysModuleDTO {
    @Null(groups = ValidGroup.Create.class)
    @NotNull(groups = ValidGroup.Update.class)
    @Schema(description = "模块id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1")
    private Long id;

    @NotBlank(message = "模块名称不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "模块名称")
    private String moduleName;

    @NotBlank(message = "模块key不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "模块key")
    private String moduleValue;

    @Schema(description = "模块图标")
    private String moduleIcon;

    @Schema(description = "模块配置")
    private String moduleConfig;

    @Schema(description = "排序值")
    private Integer sortOrder;
}

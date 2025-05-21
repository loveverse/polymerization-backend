package com.loveverse.auth.request;

import com.loveverse.core.valid.ValidGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author love
 * @since 2025/5/17 20:15
 */

@Data
@Schema(description = "角色信息")
public class SysRoleDTO {

    @Null(groups = ValidGroup.Create.class)
    @NotNull(groups = ValidGroup.Update.class)
    @Schema(description = "角色id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1")
    private Long id;

    @NotBlank(message = "角色名称不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "角色名称", example = "admin")
    private String roleName;

    @NotBlank(message = "角色权限标识不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "角色权限字符串", example = "admin")
    private String roleKey;

    @Pattern(regexp = "^[01]$", message = "角色状态只能是0或1")
    @Schema(description = "角色状态：0-停用，1-正常,默认1", example = "1")
    private Integer status;
}

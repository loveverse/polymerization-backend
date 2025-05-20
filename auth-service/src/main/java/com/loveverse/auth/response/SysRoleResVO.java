package com.loveverse.auth.response;

import com.loveverse.mybatis.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author love
 * @since 2025/5/19 15:00
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "角色信息")
public class SysRoleResVO extends BaseVO {

    @Schema(description = "角色名称", example = "admin")
    private String roleName;

    @Schema(description = "角色权限字符串", example = "admin")
    private String roleKey;

    @Schema(description = "角色状态：0-停用，1-正常,默认1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1")
    private Integer status;
}

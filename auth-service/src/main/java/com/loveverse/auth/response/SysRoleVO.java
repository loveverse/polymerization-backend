package com.loveverse.auth.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class SysRoleVO extends BaseVO {
    @Schema(description = "角色id")
    private Long id;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色权限字符串")
    private String roleKey;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "角色状态：0-停用，1-正常,默认1")
    private Integer status;
}

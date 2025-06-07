package com.loveverse.auth.response;

import com.loveverse.mybatis.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author love
 * @since 2025/5/23 15:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "模块信息")
public class SysModuleVO extends BaseVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "模块名称")
    private String moduleName;

    @Schema(description = "模块key")
    private String moduleValue;

    @Schema(description = "模块图标")
    private String moduleIcon;

    @Schema(description = "模块配置")
    private String moduleConfig;

    @Schema(description = "排序值")
    private Integer sortOrder;
}

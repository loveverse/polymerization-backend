package com.loveverse.auth.response;

import com.loveverse.core.valid.ValidGroup;
import com.loveverse.mybatis.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author love
 * @since 2025/5/22 16:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "字典信息")
public class SysDictVO extends BaseVO {

    @Schema(description = "字典id")
    private Long id;

    @Schema(description = "字典名（英文）")
    private String dictValue;

    @Schema(description = "字典名（中文名）")
    private String dictLabel;

    @Schema(description = "字典类型分组,null为通用")
    private String dictType;
}

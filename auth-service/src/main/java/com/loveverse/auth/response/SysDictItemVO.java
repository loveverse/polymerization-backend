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
 * @since 2025/5/22 17:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "字典项信息")
public class SysDictItemVO extends BaseVO {

    @Schema(description = "字典项id")
    private Long id;

    @Schema(description = "字典id")
    private Long dictId;

    @Schema(description = "字典项值")
    private String dictItemValue;

    @Schema(description = "字典项名称")
    private String dictItemLabel;

    @Schema(description = "排序值")
    private Integer sortOrder;
}

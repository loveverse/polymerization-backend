package com.loveverse.auth.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.loveverse.core.valid.ValidGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author love
 * @since 2025/5/22 17:30
 */
@Data
@Schema(description = "字典项信息")
public class SysDictItemDTO {
    @Null(groups = ValidGroup.Create.class)
    @NotNull(groups = ValidGroup.Update.class)
    @Schema(description = "字典项id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1")
    private Long id;

    @Null(groups = ValidGroup.Update.class)
    @NotNull(groups = ValidGroup.Create.class)
    @Schema(description = "字典id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1")
    private Long dictId;

    @NotBlank(message = "字典名（中文）不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "字典项值", maxLength = 100, example = "M")
    private String dictItemValue;

    @NotBlank(message = "字典名（中文）不能为空", groups = {ValidGroup.Create.class})
    @Schema(description = "字典项名称", maxLength = 100, example = "男")
    private String dictItemLabel;

    @Schema(description = "排序值", requiredMode = Schema.RequiredMode.NOT_REQUIRED, defaultValue = "1")
    private Integer sortOrder;
}

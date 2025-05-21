package com.loveverse.core.dto;

//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author love
 * @since 2025/4/3
 */
@Data
@Schema(description = "通用分页查询")
public class PageParam implements Serializable {


    // 为什么不使用int， 因为@NotNull对基本类型不生效，int默认值为0，无法使用@NotNull
    // @Min接收Long类型的值，不传时会自动进行隐式转换
    @Min(value = 1L, message = "page必须大于等于1")
    @NotNull(message = "page不能为空")
    @Schema(description = "页码，默认为1", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer page = 1;

    @Min(value = 1L, message = "size必须大于等于1")
    @Max(value = 1000L, message = "size最大为1000")
    @NotNull(message = "size不能为空")
    @Schema(description = "每页数量,默认为10，最大为1000", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    private Integer size = 10;
}

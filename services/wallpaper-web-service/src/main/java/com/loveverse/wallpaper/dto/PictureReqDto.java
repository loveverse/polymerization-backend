package com.loveverse.wallpaper.dto;


import com.loveverse.core.dto.PageParam;
import com.loveverse.wallpaper.annotation.ApiEnumDescription;
import com.loveverse.wallpaper.enums.ProportionEnum;
import com.loveverse.wallpaper.enums.ResolutionRatioEnum;
import com.loveverse.wallpaper.enums.SortEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "图片列表请求参数")
public class PictureReqDto extends PageParam {
    /**
     * 排序字段
     */
    @ApiEnumDescription(value = SortEnum.class)
    @Schema(description = "壁纸排序字段")
    private String sortField;
    /**
     * 分辨率
     */
    @ApiEnumDescription(value = ResolutionRatioEnum.class)
    @Schema(description = "壁纸分辨率")
    private int resolutionRatio;

    /**
     * 比例
     */
    @ApiEnumDescription(value = ProportionEnum.class)
    @Schema(description = "壁纸比例")
    private int proportion;

    /**
     * 主色调
     */
    @Schema(description = "壁纸主色调")
    private int primaryColor;

}

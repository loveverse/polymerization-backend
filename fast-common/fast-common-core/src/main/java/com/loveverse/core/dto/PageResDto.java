package com.loveverse.core.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author love
 * @since 2025/4/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResDto<T> {
    @JsonSerialize(using = NumberSerializers.LongSerializer.class)
    @Schema(description = "当前页数")
    private Long page;

    @JsonSerialize(using = NumberSerializers.LongSerializer.class)
    @Schema(description = "每页数量")
    private Long size;

    @JsonSerialize(using = NumberSerializers.LongSerializer.class)
    @Schema(description = "总数量")
    private Long total;

    @Schema(description = "数据")
    private List<T> data;
}

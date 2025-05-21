package com.loveverse.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.loveverse.core.json.LongJsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author love
 * @since 2025/4/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {

    // TODO 使用下面注释会报错 No qualifying bean of type 'java.lang.Class<?>' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
    //@JsonSerialize(using = NumberSerializers.LongSerializer.class)
    //@JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonSerialize(using = LongJsonSerialize.class)
    @Schema(description = "当前页数")
    private Long page;

    @JsonSerialize(using = LongJsonSerialize.class)
    @Schema(description = "每页数量")
    private Long size;

    @JsonSerialize(using = LongJsonSerialize.class)
    @Schema(description = "总数量")
    private Long total;

    @Schema(description = "数据")
    private List<T> data;
}

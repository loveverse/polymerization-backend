package com.loveverse.file.upload.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "上传文件响应")
public class FileInfoResDto {
    private Long id;

    /**
     * 文件名
     */
    @Schema(description = "文件名")
    private String name;

    /**
     * 文件url
     */
    @Schema(description = "文件路径")
    private String url;
}

package com.loveverse.oss.thirdparty.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author love
 * @since 2025/4/9
 */
@Getter
@Setter
@Schema(description = "上传文件响应")
public class FileDetailInfoResDto {
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
    /**
     * 文件大小
     */
    @Schema(description = "文件大小")
    private Long size;

    /**
     * 文件类型
     */
    @Schema(description = "文件类型")
    private String type;

    /**
     * 文件后缀
     */
    @Schema(description = "文件后缀")
    private String suffix;

    /**
     * 文件哈希
     */
    @Schema(description = "文件唯一 hash 值")
    private String hash;
}


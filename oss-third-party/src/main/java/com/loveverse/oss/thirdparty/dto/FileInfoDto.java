package com.loveverse.oss.thirdparty.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileInfoDto {
    private Long id;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件url
     */
    private String url;
    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 文件哈希
     */
    private String hash;
}

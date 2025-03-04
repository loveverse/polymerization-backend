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
    private String size;

    private String path;

    private String host;
}

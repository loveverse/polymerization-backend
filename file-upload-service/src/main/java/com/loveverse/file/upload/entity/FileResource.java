package com.loveverse.file.upload.entity;


import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.DefaultValue;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;

import com.loveverse.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "file_resource", comment = "文件表")
public class FileResource extends BaseEntity {
    @Column(comment = "文件名称", isNull = true)
    private String fileName;

    @Column(comment = "文件路径", isNull = false)
    private String fileUrl;

    @Column(comment = "文件大小", isNull = false)
    private BigDecimal fileSize;

    @Column(comment = "文件类型", isNull = false)
    private String fileType;

    @Column(comment = "文件后缀", isNull = false)
    private String suffix;

    @DefaultValue("B")
    @Column(comment = "文件单位：B/Kb/Mb/Gb/Tb默认B", defaultValue = "B", isNull = false)
    private String sizeUnit;

    //@Unique(columns = {"file_hash"})
    @Column(comment = "文件哈希，判断文件是否已经上传", isNull = false)
    private String fileHash;

}

package com.loveverse.file.upload.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loveverse.file.upload.entity.FileResource;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author love
 * @since 2025/4/8
 */
@Mapper
public interface OssFileResourceMapper extends BaseMapper<FileResource> {
}

package com.loveverse.oss.thirdparty.service.impl;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.db.DbRuntimeException;
import com.loveverse.fast.common.exception.BadRequestException;
import com.loveverse.oss.thirdparty.dto.response.FileInfoResDto;
import com.loveverse.oss.thirdparty.entity.FileResource;
import com.loveverse.oss.thirdparty.exception.DBException;
import com.loveverse.oss.thirdparty.mapper.OssFileResourceMapper;
import com.loveverse.oss.thirdparty.service.MinioFileSaveService;
import com.loveverse.oss.thirdparty.util.MinioUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

@DependsOn("minioConfig")
@RequiredArgsConstructor
@Slf4j
@Service
public class MinioFileSaveServiceImpl implements MinioFileSaveService {

    private final MinioUtils minioUtils;

    private final OssFileResourceMapper fileResourceMapper;

    Snowflake snowflake = IdUtil.getSnowflake(1L, 1L);

    /**
     * 上传文件到 MinIO
     */

    @Transactional
    @Override
    public FileInfoResDto uploadFile(MultipartFile file, String bucket) {
        FileResource fileResource = new FileResource();
        long uuid = snowflake.nextId();
        fileResource.setId(uuid);
        String originalFileName = StrUtil.blankToDefault(file.getOriginalFilename(), String.valueOf(uuid));
        fileResource.setFileName(originalFileName);
        fileResource.setFileSize(BigDecimal.valueOf(file.getSize()));
        fileResource.setFileType(file.getContentType());
        fileResource.setSuffix(FileNameUtil.getSuffix(originalFileName));

        try (InputStream is = file.getInputStream()) {
            fileResource.setFileHash(DigestUtil.sha256Hex(is));
        } catch (IOException e) {
            log.error("io异常", e);
            throw new RuntimeException(e);
        }
        // 文件url组成：域名 存储桶名 月份 文件名
        String fileUrl = minioUtils.uploadFile(file, originalFileName, bucket);
        fileResource.setFileUrl(fileUrl);
        try {
            fileResourceMapper.insert(fileResource); // 先保存到数据库
        } catch (DataAccessException e) {
            // 数据库保存错误, 删除文件
            minioUtils.removeFile(originalFileName, bucket);
            throw new DBException("数据库错误");
        }

        // 返回响应
        FileInfoResDto fileInfoResDto = new FileInfoResDto();
        fileInfoResDto.setId(uuid);
        fileInfoResDto.setName(originalFileName);
        fileInfoResDto.setUrl(fileUrl);
        return fileInfoResDto; // 返回存储路径
    }

    @Override
    public FileInfoResDto uploadPictureFile(MultipartFile file, String bucket) {
        FileInfoResDto fileInfoDto = new FileInfoResDto();
        long uuid = snowflake.nextId();
        fileInfoDto.setId(uuid);
        String originalFileName = StrUtil.blankToDefault(file.getOriginalFilename(), String.valueOf(uuid));
        fileInfoDto.setName(originalFileName);

        FileResource fileResource = new FileResource();
        fileResource.setId(uuid);
        //fileResource.setFileName(originalFileName);
        //fileResource.setFileSize(BigDecimal.valueOf(file.getSize()));
        //fileResource.setFileType(file.getContentType());

        //fileResourceMapper.insert(fileResource); // 先保存到数据库
        //String fileUrl = minioUtils.uploadFile(file, originalFileName, bucket); // 再上传到MinIO
        //fileResource.setFileUrl(fileUrl);
        //try {
        //    fileResourceMapper.updateById(fileResource); // 更新文件URL到数据库
        //} catch (Exception e) {
        //    minioUtils.deleteFile(originalFileName, bucket); // 删除MinIO中的文件
        //    throw e; // 重新抛出异常
        //}

        //fileInfoDto.setUrl(fileUrl);
        return fileInfoDto; // 返回存储路径
    }
}
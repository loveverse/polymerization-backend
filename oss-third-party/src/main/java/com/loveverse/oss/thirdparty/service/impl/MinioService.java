package com.loveverse.oss.thirdparty.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.loveverse.oss.thirdparty.config.MinioConfig;
import com.loveverse.oss.thirdparty.dto.FileInfoDto;
import com.loveverse.oss.thirdparty.util.MinioUtils;
import io.minio.errors.*;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;


import io.minio.*;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@DependsOn("minioConfig")
@RequiredArgsConstructor
@Service
public class MinioService {

    private final MinioUtils minioUtils;


    Snowflake snowflake = IdUtil.getSnowflake(1L, 1L);


    /**
     * 上传文件到 MinIO
     */
    public FileInfoDto uploadFile(MultipartFile file, String bucket) {

//        String fileHash = minioUtil.getFileChecksum(file); // 计算哈希值
        FileInfoDto fileInfoDto = new FileInfoDto();
//        if(minioUtil.fileExists(fileHash,bucket)){
//            StatObjectResponse statObjectResponse = minioClient.statObject(StatObjectArgs.builder().bucket(bucket)
//            .object(fileHash).build());
////            fileInfoDto.setId();
//            fileInfoDto.setSize(statObjectResponse.size());
//            fileInfoDto.setName(file.getOriginalFilename());
//
//        }

        long uuid = snowflake.nextId();
        fileInfoDto.setId(uuid);
        String originalFileName = file.getOriginalFilename();
        if (StrUtil.isBlank(originalFileName)) {
            originalFileName = String.valueOf(uuid);
        }
        fileInfoDto.setName(originalFileName);
        fileInfoDto.setSize(file.getSize());
        fileInfoDto.setSuffix(FileUtil.getSuffix(originalFileName));
        fileInfoDto.setType(file.getContentType());
        // 文件url组成：域名 存储桶名 月份 文件名
        String fileUrl = minioUtils.uploadFile(file, originalFileName, bucket);
        fileInfoDto.setUrl(fileUrl);
        return fileInfoDto; // 返回存储路径
    }


}


package com.loveverse.oss.thirdparty.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.loveverse.oss.thirdparty.dto.FileInfoDto;
import com.loveverse.oss.thirdparty.util.MinioUtil;
import io.minio.errors.*;
import org.springframework.stereotype.Service;


import io.minio.*;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;
    private final MinioUtil minioUtil;


    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Value("${minio.bucket}")
    private String bucket;

    Snowflake snowflake = IdUtil.getSnowflake(1L, 1L);

    /**
     * 计算文件的 SHA-256 哈希值
     */
    public String calculateFileHash(InputStream inputStream) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            digest.update(buffer, 0, bytesRead);
        }
        return Base64.getEncoder().encodeToString(digest.digest()); // 返回 Base64 编码的哈希值
    }

    /**
     * 获取当前月份路径：2024-03/
     */
    private String getCurrentMonthPath() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 检查文件是否已存在（基于文件名 + 哈希值去重）
     */
    public boolean isFileDuplicate(String fileName, String fileHash) throws Exception {
        Iterable<Result<Item>> results =
                minioClient.listObjects(ListObjectsArgs.builder().bucket(bucket).prefix(getCurrentMonthPath()).recursive(true).build());

        for (Result<Item> result : results) {
            Item item = result.get();
            if (item.objectName().contains(fileHash)) {
                return true; // 发现哈希值相同的文件，视为重复
            }
        }
        return false;
    }

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
        String monthPath = getCurrentMonthPath();
        String originalFileName = file.getOriginalFilename();
        if (StrUtil.isBlank(originalFileName)) {
            originalFileName = String.valueOf(uuid);
        }
        fileInfoDto.setName(originalFileName);
        // 文件url组成：域名 存储桶名 月份 文件名
        String fileUrl = endpoint + "/" + this.bucket + "/" + monthPath + "/" + originalFileName;
        fileInfoDto.setUrl(fileUrl);
        fileInfoDto.setSize(file.getSize());
        String suffix = FileUtil.getSuffix(originalFileName);
        fileInfoDto.setSuffix(suffix);
        String fileContentType = file.getContentType();
        fileInfoDto.setType(fileContentType);
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            minioClient.putObject(PutObjectArgs.builder().bucket(this.bucket).object(originalFileName).stream(inputStream
                    , file.getSize(), -1).contentType(fileContentType).build());
        } catch (ErrorResponseException | NoSuchAlgorithmException | IOException | InvalidResponseException |
                 ServerException | XmlParserException | InsufficientDataException | InternalException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileInfoDto; // 返回存储路径
    }


}


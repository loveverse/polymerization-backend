package com.loveverse.file.upload.util;

import cn.hutool.core.util.StrUtil;

import com.loveverse.core.exception.ServerException;
import com.loveverse.file.upload.config.MinioConfig;
import com.loveverse.file.upload.exception.FileException;
import com.loveverse.file.upload.exception.MinioException;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
@DependsOn("minioConfig")
public class MinioUtils {
    private final MinioConfig minioConfig;
    private final MinioClient minioClient;

    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 传入存储桶名称创建
     */
    public void createBucketByCondition(String bucketName) {
        // 判断桶是否存在
        boolean result = bucketExists(bucketName);
        if (!result) {
            try {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                SetBucketPolicyArgs args =
                        SetBucketPolicyArgs.builder().bucket(bucketName).config(minioConfig.generateBucketPolicy(bucketName)).build();
                minioClient.setBucketPolicy(args);
                log.info("创建桶{}成功", bucketName);
            } catch (Exception e) {
                log.error("创建桶失败：{}", e.getMessage());
                throw new MinioException("创建桶失败：{}", e.getMessage());
            }
        } else {
            log.info("名为：{} 的桶已存在！", bucketName);
        }
    }

    /**
     * 返回默认或者配置的存储桶名称
     */
    public String getBucketName(String bucket) {
        return StrUtil.isNotBlank(bucket) ? bucket : minioConfig.getBucket();
    }


    /**
     * 计算 InputStream 的 SHA-256 哈希值
     */
    //public static String calculateFileHash(InputStream inputStream) throws IOException {
    //    try (BufferedInputStream bis = new BufferedInputStream(inputStream)) {
    //        //return DigestUtils.sha256Hex(bis);
    //        return "";
    //    }
    //}
    /**
     * 计算文件的 SHA-256 哈希值
     */
    //public String calculateFileHash(InputStream inputStream) throws Exception {
    //    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    //    byte[] buffer = new byte[8192];
    //    int bytesRead;
    //    while ((bytesRead = inputStream.read(buffer)) != -1) {
    //        digest.update(buffer, 0, bytesRead);
    //    }
    //    return Base64.getEncoder().encodeToString(digest.digest()); // 返回 Base64 编码的哈希值
    //}

    // 计算 MultipartFile 的 MD5 哈希值
    //public static String getFileChecksum(MultipartFile file) throws Exception {
    //    MessageDigest md = MessageDigest.getInstance("MD5");
    //    try (InputStream is = file.getInputStream()) {
    //        byte[] buffer = new byte[1024];
    //        int bytesRead;
    //        while ((bytesRead = is.read(buffer)) != -1) {
    //            md.update(buffer, 0, bytesRead);
    //        }
    //    }
    //    byte[] digest = md.digest();
    //    return new BigInteger(1, digest).toString(16); // 转换为 16 进制字符串
    //}

    // 检查 MinIO 是否已有该文件
    //public static boolean fileExists(String objectName, String bucket) {
    //    try {
    //        minioClient.statObject(StatObjectArgs.builder().bucket(bucket).object(objectName).build());
    //        return true; // 存在
    //    } catch (Exception e) {
    //        return false; // 不存在
    //    }
    //}


    /**
     * 检查文件是否已存在（基于文件名 + 哈希值去重）
     */
    //public static boolean isFileDuplicate(String fileName, String fileHash) throws Exception {
    //    Iterable<Result<Item>> results =
    //            minioClient.listObjects(ListObjectsArgs.builder().bucket(minioConfig.getBucket()).prefix
    //            (getCurrentMonthPath()).recursive(true).build());
    //
    //    for (Result<Item> result : results) {
    //        Item item = result.get();
    //        if (item.objectName().contains(fileHash)) {
    //            return true; // 发现哈希值相同的文件，视为重复
    //        }
    //    }
    //    return false;
    //}

    /**
     * 获取当前日期
     */
    public String getCurrentMonthPath() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 上传文件
     */
    public String uploadFile(MultipartFile file, String fileName, String bucket) {
        /*
         * 1.判断是否传入存储桶，没有传用默认的loveverse
         * 2.计算文件哈希去重，查询minio对应存储桶文件是否存在
         * 3.上传文件返回文件id,url,name
         * */
        // 传了存储桶，使用传入的，没传使用默认的 loveverse
        String bucketName = getBucketName(bucket);
        // 没有的默认创建，有的啥也不干
        createBucketByCondition(bucketName);
        String fileContentType = file.getContentType();
        String path = getCurrentMonthPath() + "/" + fileName;
        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(path).stream(inputStream,
                    file.getSize(), -1).contentType(fileContentType).build());
        } catch (Exception e) {
            log.error("上传文件失败{}", e.getMessage());
            throw new FileException("上传文件失败", e.getMessage());
        }
        return minioConfig.getEndpoint() + "/" + bucketName + "/" + path;
    }

    public void removeFile(String fileName, String bucket) {
        try {
            // 传了存储桶，使用传入的，没传使用默认的 loveverse
            String bucketName = getBucketName(bucket);
            String path = getCurrentMonthPath() + "/" + fileName;
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(path).build());
        } catch (Exception e) {
            log.error("删除文件失败{}", e.getMessage());
            throw new FileException("上传文件失败", e.getMessage());
        }
    }
}

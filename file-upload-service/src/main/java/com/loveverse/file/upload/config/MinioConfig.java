package com.loveverse.file.upload.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loveverse.core.exception.ServerException;
import com.loveverse.file.upload.exception.MinioException;
import io.minio.MinioClient;
import io.minio.SetBucketPolicyArgs;
import io.minio.messages.Bucket;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String endpoint;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
        setBucketPolicies(minioClient);
        return minioClient;
    }


    public void setBucketPolicies(MinioClient minioClient) {
        try {
            List<Bucket> buckets = minioClient.listBuckets();
            for (Bucket bucket : buckets) {
                applyPublicReadPolicy(minioClient, bucket.name());
            }
        } catch (Exception e) {
            log.error("设置桶策略错误: {}", e.getMessage());
            throw new MinioException("设置桶策略错误", e.getCause());
        }
    }

    private void applyPublicReadPolicy(MinioClient minioClient, String bucketName) {
        try {
            String policyJson = generateBucketPolicy(bucketName);
            SetBucketPolicyArgs args = SetBucketPolicyArgs.builder()
                    .bucket(bucketName)
                    .config(policyJson)
                    .build();
            minioClient.setBucketPolicy(args);
            log.info("应用于存储桶的公有读取策略: {}", bucketName);
        } catch (Exception e) {
            log.error("无法为 {} 应用策略 : {}", bucketName, e.getMessage());
            throw new MinioException("无法为 {} 应用策略 : {}", bucketName, e.getMessage());
        }
    }

    public String generateBucketPolicy(String bucketName) throws Exception {
        Map<String, Object> policyMap = new HashMap<>();
        policyMap.put("Version", "2012-10-17");

        Map<String, Object> statement = new HashMap<>();
        statement.put("Effect", "Allow");

        Map<String, Object> principal = new HashMap<>();
        principal.put("AWS", Collections.singletonList("*"));

        statement.put("Principal", principal);
        statement.put("Action", Collections.singletonList("s3:GetObject"));
        statement.put("Resource", Collections.singletonList("arn:aws:s3:::" + bucketName + "/*"));

        policyMap.put("Statement", Collections.singletonList(statement));

        return objectMapper.writeValueAsString(policyMap);
    }


}

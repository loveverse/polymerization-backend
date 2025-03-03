package com.loveverse.oss.thirdparty.controller;

import com.loveverse.fast.common.enums.ErrorCodeEnum;
import com.loveverse.fast.common.util.BaseResponse;
import com.loveverse.fast.common.util.ResultUtils;
import com.loveverse.oss.thirdparty.dto.FileInfoDto;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@RestController
@Api(tags = "上传文件")
@RequestMapping("/file")
public class FileController {
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Value("${minio.bucket}")
    private String bucket;

    @GetMapping("/upload")
    @ApiOperation(value = "上传文件到minio存储对象",httpMethod = "GET")
    public BaseResponse<FileInfoDto> policy(@RequestParam("pic") String pic) {
        UUID uuid = UUID.randomUUID();
        String name = uuid + "-" + pic; // 修改文件名防止上传相同文件被覆盖
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dir = dateTimeFormatter.format(LocalDateTime.now()); // 用户上传文件时指定的前缀，即上传目录。
        pic = dir + "/" + name;// 文件上传目录+名称
        String host = "";
        String path = "/" + bucket + "/" + pic;// 该值暂没用到，前端不熟直接使用没用域名的图片地址不知道怎么处理

        try {

            MinioClient minioClient = MinioClient.builder().endpoint(endpoint)
                    .credentials(accessKey, secretKey).build();
            Map<String, String> reqParams = new HashMap<>();
            reqParams.put("response-content-type", "application/json");
            //reqParams.put("response-content-type", "image/jpeg");  // 或者根据上传的文件类型动态设置

            host = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)//这里必须是PUT，如果是GET的话就是文件访问地址了。如果是POST上传会报错.
                            .bucket(bucket)
                            .object(pic)
                            .expiry(60 * 60 * 24)
                            .extraQueryParams(reqParams)
                            .build());
            System.out.println(host); // 前端直传需要的url地址
            FileInfoDto fileInfoDto = new FileInfoDto();
            long date = new Date().getTime();
            fileInfoDto.setId(date);
            fileInfoDto.setName(name);
            fileInfoDto.setUrl(endpoint + path);// Constant.MINIO_URL自己的minio服务器地址
            fileInfoDto.setHost(host);
            fileInfoDto.setPath(path);
            return ResultUtils.success(fileInfoDto);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            return ResultUtils.error(ErrorCodeEnum.OPERATION_ERROR, "Failed to generate policy: " + e.getMessage());
        }

    }
}
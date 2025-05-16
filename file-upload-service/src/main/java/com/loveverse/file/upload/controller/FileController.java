package com.loveverse.file.upload.controller;

import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import com.loveverse.file.upload.dto.response.FileInfoResDto;
import com.loveverse.file.upload.entity.FileResource;
import com.loveverse.file.upload.service.MinioFileSaveService;

import com.loveverse.redis.util.RedisUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "上传文件")
@RequestMapping("/file")
public class FileController {

    private final MinioFileSaveService minioService;
    private final RedisUtils redisUtils;



    /**
     * 上传文件
     *
     * @param file file
     * @return FileInfoResDto
     */
    @Operation(summary = "获取 MinIO 直传 URL")
    @PostMapping("/upload")
    public ResponseData<FileInfoResDto> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(value =
            "bucket", required = false) String bucket) {
        FileInfoResDto fileInfoDto = minioService.uploadFile(file, bucket);
        return ResponseCode.SUCCESS.getResponse(fileInfoDto);
    }

    @Operation(summary = "图片上传（支持去重）")
    @PostMapping("/upload/picture")
    public ResponseData<FileInfoResDto> uploadPictureFile(@RequestParam("file") MultipartFile file, @RequestParam(value =
            "bucket", required = false) String bucket) {
        FileInfoResDto fileInfoDto = minioService.uploadPictureFile(file, bucket);
        return ResponseCode.SUCCESS.getResponse(fileInfoDto);
    }

    @GetMapping("/hello")
    public ResponseData<String> hello() {
        redisUtils.set("cc","测试数据",100);
        return ResponseCode.SUCCESS.getResponse("hello");
    }

    @GetMapping("/info/{id}")
    public ResponseData<FileResource> file(@PathVariable("id") Long id) {
        FileResource fileInfoResDto = minioService.getFileInfo(id);
        return ResponseCode.SUCCESS.getResponse(fileInfoResDto);
    }
    @GetMapping("/info")
    public ResponseData<FileResource> file2(@RequestParam("id") Long id) {
        FileResource fileInfoResDto = minioService.getFileInfo(id);
        return ResponseCode.SUCCESS.getResponse(fileInfoResDto);
    }
}

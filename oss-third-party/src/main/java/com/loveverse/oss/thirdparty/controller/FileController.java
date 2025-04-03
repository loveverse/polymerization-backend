package com.loveverse.oss.thirdparty.controller;

import com.loveverse.fast.common.http.ResponseCode;
import com.loveverse.fast.common.http.ResponseData;
import com.loveverse.oss.thirdparty.dto.FileInfoDto;
import com.loveverse.oss.thirdparty.service.impl.MinioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "上传文件")
@RequestMapping("/file")
public class FileController {

    private final MinioService minioService;

    /**
     * 上传文件
     *
     * @param file file
     * @return FileInfoDto
     */
    @Operation(summary = "获取 MinIO 直传 URL")
    @PostMapping("/upload")
    public ResponseData<FileInfoDto> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(value =
            "bucket", required = false) String bucket) {
        FileInfoDto fileInfoDto = minioService.uploadFile(file, bucket);
        return ResponseCode.SUCCESS.getResponse(fileInfoDto);
    }
    @GetMapping("/hello")
    public ResponseData<String> hello(){
        return ResponseCode.SUCCESS.getResponse("hello");
    }
}
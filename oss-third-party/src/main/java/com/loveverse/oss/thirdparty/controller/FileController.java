package com.loveverse.oss.thirdparty.controller;

import com.loveverse.fast.common.http.ResponseCode;
import com.loveverse.fast.common.http.ResponseData;
import com.loveverse.oss.thirdparty.dto.response.FileInfoResDto;
import com.loveverse.oss.thirdparty.entity.FileResource;
import com.loveverse.oss.thirdparty.service.MinioFileSaveService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    private final MinioFileSaveService minioService;

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

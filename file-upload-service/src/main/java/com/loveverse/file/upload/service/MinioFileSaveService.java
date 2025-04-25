package com.loveverse.file.upload.service;

import com.loveverse.file.upload.dto.response.FileInfoResDto;
import com.loveverse.file.upload.entity.FileResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author love
 * @since 2025/4/9
 */
public interface MinioFileSaveService {
    FileInfoResDto uploadFile(MultipartFile file, String bucket);

    FileInfoResDto uploadPictureFile(MultipartFile file, String bucket);

    FileResource getFileInfo(Long id);
}

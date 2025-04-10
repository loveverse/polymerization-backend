package com.loveverse.oss.thirdparty.service;

import com.loveverse.oss.thirdparty.dto.response.FileInfoResDto;
import com.loveverse.oss.thirdparty.entity.FileResource;
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

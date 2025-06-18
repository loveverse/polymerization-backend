package com.loveverse.auth.service;

import com.loveverse.auth.entity.ComExcelTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author love
 * @since 2025/6/17 16:16
 */
public interface ComExcelTemplateService {
    ComExcelTemplate getExcel(String key);

    void saveExcel(MultipartFile file, String key, String remark) throws IOException;

    List<ComExcelTemplate> getExcelList();
}

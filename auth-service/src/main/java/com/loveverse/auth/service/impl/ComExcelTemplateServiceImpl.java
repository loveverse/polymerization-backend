package com.loveverse.auth.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loveverse.auth.entity.ComExcelTemplate;
import com.loveverse.auth.mapper.ComExcelTemplateMapper;
import com.loveverse.auth.service.ComExcelTemplateService;
import com.loveverse.core.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * @author love
 * @since 2025/6/17 16:17
 */
@RequiredArgsConstructor
@Service
public class ComExcelTemplateServiceImpl extends ServiceImpl<ComExcelTemplateMapper, ComExcelTemplate> implements ComExcelTemplateService {


    @Override
    public ComExcelTemplate getExcel(String key) {
        ComExcelTemplate comExcelTemplate = getOne(Wrappers.<ComExcelTemplate>lambdaQuery().eq(ComExcelTemplate::getId, key));
        if (comExcelTemplate == null) {
            throw new BadRequestException("获取模板失败");
        }
        return comExcelTemplate;
    }

    @Override
    public void saveExcel(MultipartFile file, String key, String remark) throws IOException {
        byte[] bytes = IoUtil.readBytes(file.getInputStream(), true);
        String base64Encoded = Base64.encode(bytes);
        ComExcelTemplate comExcelTemplate = getOne(Wrappers.<ComExcelTemplate>lambdaQuery()
                .eq(ComExcelTemplate::getId, key)
                .select(ComExcelTemplate::getId));
        ComExcelTemplate entity = new ComExcelTemplate();
        entity.setId(key);
        entity.setRemark(remark);
        entity.setBase64(base64Encoded);
        if (comExcelTemplate == null) {
            save(entity);
        } else {
            updateById(entity);
        }
    }

    @Override
    public List<ComExcelTemplate> getExcelList() {
        return list(Wrappers.<ComExcelTemplate>lambdaQuery()
                .select(ComExcelTemplate::getId, ComExcelTemplate::getRemark, ComExcelTemplate::getCreateTime, ComExcelTemplate::getUpdateTime)
        );
    }
}

package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.entity.SysDictItem;
import com.loveverse.auth.mapper.SysDictItemMapper;
import com.loveverse.auth.request.SysDictItemDTO;
import com.loveverse.auth.response.SysDictItemVO;
import com.loveverse.auth.service.SysDictItemService;
import com.loveverse.core.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author love
 * @since 2025/5/22 17:21
 */
@RequiredArgsConstructor
@Service
public class SysDictItemServiceImpl implements SysDictItemService {
    private final SysDictItemMapper sysDictItemMapper;

    @Override
    public void createDictItem(SysDictItemDTO sysDictItemDTO) {
        SysDictItem sysDictItem = new SysDictItem();
        BeanUtils.copyProperties(sysDictItemDTO, sysDictItem);
        sysDictItemMapper.insert(sysDictItem);
    }

    @Override
    public void deleteDictItem(String id) {
        sysDictItemMapper.deleteById(id);
    }

    @Override
    public void updateDictItem(SysDictItemDTO sysDictItemDTO) {
        SysDictItem data = sysDictItemMapper.selectById(sysDictItemDTO.getId());
        if (data == null) {
            throw new BadRequestException("不存在该字典项");
        }
        BeanUtils.copyProperties(sysDictItemDTO, data);
        sysDictItemMapper.updateById(data);
    }

    @Override
    public List<SysDictItemVO> getDictItemList() {
        List<SysDictItem> sysDictItemList = sysDictItemMapper.selectList(Wrappers.lambdaQuery());
        return Optional.ofNullable(sysDictItemList).orElse(Collections.emptyList()).stream().map(item -> {
            SysDictItemVO sysDictItemVO = new SysDictItemVO();
            BeanUtils.copyProperties(item, sysDictItemVO);
            return sysDictItemVO;
        }).collect(Collectors.toList());
    }
}

package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.entity.SysDict;
import com.loveverse.auth.mapper.SysDictMapper;
import com.loveverse.auth.request.SysDictDTO;
import com.loveverse.auth.response.SysDictVO;
import com.loveverse.auth.service.SysDictService;
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
 * @since 2025/5/22 16:32
 */
@RequiredArgsConstructor
@Service
public class SysDictServiceImpl implements SysDictService {
    private final SysDictMapper sysDictMapper;

    @Override
    public void createDict(SysDictDTO sysDictDto) {
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(sysDictDto, sysDict);
        sysDictMapper.insert(sysDict);
    }

    @Override
    public void deleteDict(String id) {
        sysDictMapper.deleteById(id);
    }

    @Override
    public void updateDict(SysDictDTO sysDictDto) {
        SysDict data = sysDictMapper.selectById(sysDictDto.getId());
        if (data == null) {
            throw new BadRequestException("不存在该字典");
        }
        BeanUtils.copyProperties(sysDictDto, data);
        sysDictMapper.updateById(data);
    }

    @Override
    public List<SysDictVO> getDictList() {
        List<SysDict> sysDictList = sysDictMapper.selectList(Wrappers.lambdaQuery());
        return Optional.ofNullable(sysDictList).orElse(Collections.emptyList()).stream().map(item -> {
            SysDictVO sysDictVO = new SysDictVO();
            BeanUtils.copyProperties(item, sysDictVO);
            return sysDictVO;
        }).collect(Collectors.toList());
    }

    @Override
    public void createDictItem(SysDictDTO sysDictDto) {

    }

    @Override
    public void deleteDictItem(String id) {

    }
}

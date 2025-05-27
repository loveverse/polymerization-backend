package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.converter.SystemConverter;
import com.loveverse.auth.entity.SysDict;
import com.loveverse.auth.entity.SysDictItem;
import com.loveverse.auth.entity.SysModule;
import com.loveverse.auth.mapper.SysDictItemMapper;
import com.loveverse.auth.mapper.SysDictMapper;
import com.loveverse.auth.mapper.SysModuleMapper;
import com.loveverse.auth.request.SysModuleDTO;
import com.loveverse.auth.response.SysModuleVO;
import com.loveverse.auth.service.SysModuleService;
import com.loveverse.core.exception.BadRequestException;
import com.loveverse.mybatis.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author love
 * @since 2025/5/23 15:38
 */
@RequiredArgsConstructor
@Service
public class SysModuleServiceImpl implements SysModuleService {
    private final SysModuleMapper sysModuleMapper;
    private final SysDictMapper sysDictMapper;
    private final SysDictItemMapper sysDictItemMapper;
    private final SystemConverter systemConverter;

    @Override
    public void createModule(SysModuleDTO sysModuleDTO) {
        sysModuleMapper.insert(systemConverter.convertModuleToEntity(sysModuleDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteModule(String id) {
        sysModuleMapper.deleteById(id);
        List<SysDict> sysDictList = sysDictMapper.selectList(Wrappers.<SysDict>lambdaUpdate().eq(SysDict::getModuleId, id));
        if (!CollectionUtils.isEmpty(sysDictList)) {
            List<Long> dictIds = sysDictList.stream().map(SysDict::getId).collect(Collectors.toList());
            sysDictMapper.delete(Wrappers.<SysDict>lambdaUpdate().in(SysDict::getId, dictIds));
            sysDictItemMapper.delete(Wrappers.<SysDictItem>lambdaUpdate().in(SysDictItem::getDictId, dictIds));
        }

    }

    @Override
    public void updateModule(SysModuleDTO sysModuleDTO) {
        SysModule sysModule = sysModuleMapper.selectById(sysModuleDTO.getId());
        if (sysModule == null) {
            throw new BadRequestException("不存在该记录");
        }
        sysModuleMapper.updateById(systemConverter.convertModuleToEntity(sysModuleDTO));
    }

    @Override
    public List<SysModuleVO> getModuleList() {
        List<SysModule> sysModules = sysModuleMapper.selectList(Wrappers.lambdaQuery());
        return Optional.ofNullable(sysModules).orElse(Collections.emptyList()).stream()
                .map(systemConverter::convertModuleToVO)
                .sorted(Comparator.comparing(SysModuleVO::getSortOrder)).collect(Collectors.toList());
    }
}

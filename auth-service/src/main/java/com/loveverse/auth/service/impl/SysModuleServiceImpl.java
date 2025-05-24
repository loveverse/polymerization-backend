package com.loveverse.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.entity.SysModule;
import com.loveverse.auth.mapper.SysModuleMapper;
import com.loveverse.auth.request.SysModuleDTO;
import com.loveverse.auth.response.SysModuleVO;
import com.loveverse.auth.service.SysModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    @Override
    public void createModule(SysModuleDTO sysModuleReqDTO) {
        SysModule sysModule = new SysModule();
        BeanUtils.copyProperties(sysModuleReqDTO, sysModule);
        sysModuleMapper.insert(sysModule);
    }

    @Override
    public void deleteModule(String id) {

    }

    @Override
    public void updateModule(SysModuleDTO sysModuleReqDTO) {

    }

    @Override
    public List<SysModuleVO> getModuleList() {
        List<SysModule> sysModules = sysModuleMapper.selectList(Wrappers.lambdaQuery());
        return Optional.ofNullable(sysModules).orElse(Collections.emptyList()).stream().map(item -> {
            SysModuleVO sysModuleVO = new SysModuleVO();
            BeanUtils.copyProperties(item, sysModuleVO);
            return sysModuleVO;
        }).collect(Collectors.toList());
    }
}

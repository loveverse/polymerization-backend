package com.loveverse.auth.service.impl;

import com.loveverse.auth.request.SysModuleDTO;
import com.loveverse.auth.response.SysModuleVO;
import com.loveverse.auth.service.SysModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author love
 * @since 2025/5/23 15:38
 */
@RequiredArgsConstructor
@Service
public class SysModuleServiceImpl implements SysModuleService {
    @Override
    public void createModule(SysModuleDTO sysModuleReqDTO) {

    }

    @Override
    public void deleteModule(String id) {

    }

    @Override
    public void updateModule(SysModuleDTO sysModuleReqDTO) {

    }

    @Override
    public List<SysModuleVO> getModuleList() {
        return Collections.emptyList();
    }
}

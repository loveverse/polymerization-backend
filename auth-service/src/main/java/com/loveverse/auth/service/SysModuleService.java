package com.loveverse.auth.service;

import com.loveverse.auth.request.SysModuleDTO;
import com.loveverse.auth.response.SysModuleVO;

import java.util.List;

/**
 * @author love
 * @since 2025/5/23 15:38
 */
public interface SysModuleService  {
    void createModule(SysModuleDTO sysModuleReqDTO);

    void deleteModule(Long id);

    void updateModule(SysModuleDTO sysModuleReqDTO);

    List<SysModuleVO> getModuleList();
}

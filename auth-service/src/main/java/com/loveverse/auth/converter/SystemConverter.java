package com.loveverse.auth.converter;

import com.loveverse.auth.entity.SysModule;
import com.loveverse.auth.entity.SysRole;
import com.loveverse.auth.request.SysModuleDTO;
import com.loveverse.auth.request.SysRoleDTO;
import com.loveverse.auth.response.SysModuleVO;
import com.loveverse.auth.response.SysRoleVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


/**
 * @author love
 * @since 2025/5/24 11:27
 */
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE // 全局忽略未映射的源字段
)
public interface SystemConverter {
    //@Mapping(target = "createTime", ignore = true)
    //@Mapping(target = "updateTime", ignore = true)
    //@Mapping(target = "valid", ignore = true)
    //@Mapping(target = "version", ignore = true)
    // SysRole 存在 valid version 等，需要手动忽略或者全局配置，不然生成的代码不会赋值
    SysRole convertRoleToEntity(SysRoleDTO sysRoleDTO);

    SysRoleVO convertRoleToVO(SysRole sysRole);

    SysModule convertModuleToEntity(SysModuleDTO sysModuleDTO);

    SysModuleVO convertModuleToVO(SysModule sysModule);

}

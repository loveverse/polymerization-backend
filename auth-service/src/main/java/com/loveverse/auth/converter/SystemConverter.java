package com.loveverse.auth.converter;

import com.loveverse.auth.entity.SysRole;
import com.loveverse.auth.request.SysRoleDTO;
import org.mapstruct.Mapper;


/**
 * @author love
 * @since 2025/5/24 11:27
 */
@Mapper(componentModel = "spring")
public interface SystemConverter {

    //SysRoleVO convertRoleToEntity(SysRole sysRole);
    SysRole convertRoleToEntity(SysRoleDTO sysRoleDTO);


}

package com.loveverse.auth.converter;

import com.loveverse.auth.entity.SysDict;
import com.loveverse.auth.entity.SysModule;
import com.loveverse.auth.entity.SysRole;
import com.loveverse.auth.request.SysDictDTO;
import com.loveverse.auth.request.SysModuleDTO;
import com.loveverse.auth.request.SysRoleDTO;
import com.loveverse.auth.response.SysDictVO;
import com.loveverse.auth.response.SysModuleVO;
import com.loveverse.auth.response.SysRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


/**
 * @author love
 * @since 2025/5/24 11:27
 */
@Mapper(componentModel = "spring"/*,
        unmappedSourcePolicy = ReportingPolicy.IGNORE // 全局忽略未映射的源字段*/
)
public interface SystemConverter {
    SystemConverter INSTANCE = Mappers.getMapper(SystemConverter.class);

    //@Mapping(target = "createTime", ignore = true)
    //@Mapping(target = "updateTime", ignore = true)
    //@Mapping(target = "valid", ignore = true)
    //@Mapping(target = "version", ignore = true)
    // SysRole 存在 valid version 等，需要手动忽略或者全局配置，不然生成的代码不会赋值
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "valid", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    SysRole convertRoleToEntity(SysRoleDTO sysRoleDTO);

    SysRoleVO convertRoleToVO(SysRole sysRole);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "valid", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    SysModule convertModuleToEntity(SysModuleDTO sysModuleDTO);

    SysModuleVO convertModuleToVO(SysModule sysModule);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "valid", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    SysDict convertDictToEntity(SysDictDTO sysDictDTO);

    SysDictVO convertDictToVO(SysDict sysDict);

    //SysDictItem convertDictItemToEntity(SysDictItemDTO sysDictItemDTO);
    //
    //SysDictItemVO convertDictItemToVO(SysDictItem sysDictItem);
}

package com.loveverse.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loveverse.auth.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author love
 * @since 2025/4/25
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectPermissionByUserId(Long id);
}

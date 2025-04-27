package com.loveverse.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loveverse.auth.entity.SystemMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author love
 * @since 2025/4/25
 */
@Mapper
public interface MenuMapper extends BaseMapper<SystemMenu> {

    List<String> selectPermissionByUserId(Long id);
}

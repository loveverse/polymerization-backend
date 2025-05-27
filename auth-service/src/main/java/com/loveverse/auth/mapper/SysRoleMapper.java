package com.loveverse.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loveverse.auth.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author love
 * @since 2025/5/17 20:04
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
     List<SysRole> findRoleListByUserId(@Param("userId") Long userId);
}

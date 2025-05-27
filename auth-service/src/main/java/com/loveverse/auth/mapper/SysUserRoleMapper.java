package com.loveverse.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loveverse.auth.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * @author love
 * @since 2025/5/26 17:26
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    void insertBatch(ArrayList<SysUserRole> userRoles);
}

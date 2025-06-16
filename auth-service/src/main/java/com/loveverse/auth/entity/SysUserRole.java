package com.loveverse.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.loveverse.auth.response.SysRoleVO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author love
 * @since 2025/4/23
 */
@Data
@NoArgsConstructor
@TableName("sys_user_role")
@Table(name = "sys_user_role", comment = "用户角色表")
public class SysUserRole {

    @Unique(value = "user_role", columns = {"user_id", "role_id"})
    @Column(comment = "用户id", isNull = false)
    private Long userId;

    @Column(comment = "角色id", isNull = false)
    private Long roleId;

    @TableField(exist = false)
    private SysRoleVO roleInfo;
}

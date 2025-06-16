package com.loveverse.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author love
 * @since 2025/4/23
 */
@Data
@NoArgsConstructor
@TableName("sys_role_menu")
@Table(name = "sys_role_menu", comment = "角色菜单")
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    // 使用联合唯一主键替换联合主键,但会影响性能
    @Unique(value = "role_menu", columns = {"role_id", "menu_id"})
    @Column(comment = "角色id", isNull = false)
    private Long roleId;

    @Column(comment = "菜单id", isNull = false)
    private Long menuId;
}

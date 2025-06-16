package com.loveverse.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.loveverse.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author love
 * @since 2025/4/23
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@Table(name = "sys_user", comment = "用户表")
public class SysUser extends BaseEntity {
    @TableId(type = IdType.AUTO)
    @Column(comment = "id", isAutoIncrement = true, isKey = true)
    private Long id;

    // valid 放左边表示有效记录唯一，已删除记录可重复；valid 放右边表示所有记录强制唯一（包括已删除的）
    @Unique(value = "username", columns = {"valid", "username"})
    @Column(comment = "用户名", length = 64, isNull = false)
    private String username;

    @Column(comment = "昵称", length = 64)
    private String nickname;

    @Column(comment = "密码", length = 64, isNull = false)
    private String password;

    @Column(comment = "头像地址")
    private String avatar;

    @Column(comment = "性别：U-未知，M-男，W-女", length = 1, isNull = false, defaultValue = "U")
    private String gender;

    @Column(comment = "状态：0-停用，1-正常", length = 1, isNull = false, defaultValue = "1")
    private Integer status;

    @Column(comment = "手机号", length = 32)
    private String phoneNumber;

    @Column(comment = "邮箱", length = 64)
    private String email;


}

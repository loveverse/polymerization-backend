package com.loveverse.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableCharset;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableEngine;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import com.loveverse.mybatis.entity.BaseEntity;
import lombok.Builder;
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
public class SystemUser extends BaseEntity {
    @TableId(type = IdType.AUTO)
    @Column(comment = "id", isAutoIncrement = true, isKey = true)
    private Long id;

    @Column(comment = "用户名", length = 64, isNull = false)
    private String userName;

    @Column(comment = "昵称", length = 64)
    private String nickName;

    @Column(comment = "用户名", length = 64, isNull = false)
    private String password;

    @Column(comment = "性别：U-未知，M-男，W-女", length = 2, isNull = false, defaultValue = "M")
    private String sex;

    @Column(comment = "状态：0-停用，1-正常", length = 1, isNull = false, defaultValue = "1")
    private String status;

    @Column(comment = "手机号", length = 32)
    private String phoneNumber;

    @Column(comment = "邮箱", length = 64)
    private String email;

    @Column(comment = "用户类型：0-管理员，1-普通用户", length = 1,isNull = false, defaultValue = "1")
    private String userType;

}

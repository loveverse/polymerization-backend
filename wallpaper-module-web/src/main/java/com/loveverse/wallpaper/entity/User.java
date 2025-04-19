package com.loveverse.wallpaper.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;

import com.loveverse.mybatis.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;


// 生成EqualsAndHashCode时，包含父类的字段
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@TableCharset(MySqlCharsetConstant.UTF8MB4)
@TableEngine(MySqlEngineConstant.InnoDB)
@Table(name = "wallpaper_user", comment = "用户表")
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(comment = "用户名", isNull = false)
    @Unique(columns = "username")
    private String username;

    @Column(comment = "密码", isNull = false)
    private String password;

    @Column(comment = "用户头像")
    private String userAvatar;

    @Column(comment = "角色保留字段")
    private String userRole;
}

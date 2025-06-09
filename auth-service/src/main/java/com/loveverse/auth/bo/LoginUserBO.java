package com.loveverse.auth.bo;

import com.loveverse.auth.entity.SysRole;
import com.loveverse.auth.entity.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author love
 * @since 2025/4/25
 */
@Data
@NoArgsConstructor
public class LoginUserBO implements UserDetails {
    private static final long serialVersionUID = 1L;

    // 用户信息
    private SysUser user;
    // 用户权限标识列表
    private transient List<SimpleGrantedAuthority> authorities;

    public LoginUserBO(SysUser user, List<SysRole> roleList) {
        this.user = user;
        // 通常不能将用户菜单放在登录一起返回,如果这个用户角色或者菜单改变,他拿到还是上一次,时效性较低
        this.authorities = roleList.stream().map(item -> new SimpleGrantedAuthority(item.getRoleKey())).collect(Collectors.toList());
    }

    // 返回用户的权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 用户拥有的权限列表
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // "状态：0-停用，1-正常"
        return Objects.equals(user.getStatus(), 1);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

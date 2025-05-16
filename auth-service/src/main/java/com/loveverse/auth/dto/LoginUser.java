package com.loveverse.auth.dto;

import com.loveverse.auth.entity.SystemUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author love
 * @since 2025/4/25
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {
    private SystemUser user;

    private List<String> permissions;


    public LoginUser(SystemUser user, List<String> permissions) {
        this.user = user;
        // 通常不能将用户菜单放在登录一起返回,如果这个用户角色或者菜单改变,他拿到还是上一次,时效性较低
        this.permissions = permissions;
    }


    private List<SimpleGrantedAuthority> authorities;

    // 返回用户的权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        // 用户拥有的权限列表
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // "状态：0-停用，1-正常"
        return "1".equals(user.getStatus());
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

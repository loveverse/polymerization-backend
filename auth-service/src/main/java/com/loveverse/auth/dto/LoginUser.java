package com.loveverse.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.loveverse.auth.entity.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
        this.permissions = permissions;
    }

    private List<SimpleGrantedAuthority> authorities;

    @JsonIgnore  // 防止存入redis时序列化出错，不进行序列化，也不存入redis中
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities != null){
            return authorities;
        }
        // 用户拥有的权限列表
        return Collections.emptyList();
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
        return true;
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

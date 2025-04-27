package com.loveverse.auth.dto;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities != null){
            return authorities;
        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

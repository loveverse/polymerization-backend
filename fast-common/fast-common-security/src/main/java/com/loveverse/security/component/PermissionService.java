package com.loveverse.security.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @author love
 * @since 2025/5/19 16:52
 */
public class PermissionService {
    public boolean hasPermission(String... permissions) {
        if (permissions == null || permissions.length == 0) {
            return false;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().map(GrantedAuthority::getAuthority).
                filter(StringUtils::hasText).anyMatch(x -> PatternMatchUtils.simpleMatch(permissions, x));
    }
}

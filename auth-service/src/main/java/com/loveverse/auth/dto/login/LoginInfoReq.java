package com.loveverse.auth.dto.login;

import lombok.Data;

/**
 * @author love
 * @since 2025/4/28
 */
@Data
public class LoginInfoReq {
    private String username;
    private String password;
}

package com.loveverse.auth.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author love
 * @since 2025/4/28
 */
@Data
@Schema(description = "登录返回信息")
public class LoginInfoRes {
    private String token;

    private SystemUserDto user;

}

package com.loveverse.auth;

import com.loveverse.auth.bo.LoginUserBO;
import com.loveverse.auth.entity.SysUser;
import com.loveverse.auth.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author love
 * @since 2025/6/4 15:14
 */
@RestController

public class TestTokenExpired {
    private final JwtTokenUtil jwtTokenUtil;

    public TestTokenExpired(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/login")
    public Map<String, String> login() {
        LoginUserBO userBO = new LoginUserBO();
        SysUser sysUser = new SysUser();
        sysUser.setUsername("123");
        userBO.setUser(sysUser);
        String token = jwtTokenUtil.generateToken(userBO);
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("token",token);
        return objectObjectHashMap;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, secured world!";
    }
}

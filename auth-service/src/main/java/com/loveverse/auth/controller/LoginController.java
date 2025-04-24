package com.loveverse.auth.controller;

import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author love
 * @since 2025/4/23
 */
@RestController
@RequestMapping("/system")
public class LoginController {

    @PostMapping("v1/user/login")
    public ResponseData<Void> login(){

        return ResponseCode.SUCCESS.getResponse("成功");
    }
}

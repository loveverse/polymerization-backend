package com.loveverse.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author love
 * @since 2025/4/19
 */
@RestController
@RequestMapping("/system")
public class Hello {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}

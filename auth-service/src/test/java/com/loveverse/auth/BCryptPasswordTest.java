package com.loveverse.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author love
 * @since 2025/5/12 10:04
 */
@SpringBootTest
public class BCryptPasswordTest {
    @Test
    public void generationPassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = "admin";
        String passHash = bCryptPasswordEncoder.encode(password);
        System.out.println(passHash);

        boolean matches = bCryptPasswordEncoder.matches(password, passHash);
        System.out.println(matches);

    }
}

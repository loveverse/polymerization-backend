package com.loveverse.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author love
 * @since 2025/4/28
 */
@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /*
    *     header: ZRT-SMART-TOKEN
    # 令牌前缀 固定不要改 要改就改七个字符的
    token-start-with: Bearer
    # 必须使用最少88位的Base64对该令牌进行编码
    base64-secret: ZmQ0ZGI5NjQ0MDQwY2I4MjMxY2Y3ZmI3MjdhN2ZmMjNhODViOTg1ZGE0NTBjMGM4NDA5NzYxMjdjOWMwYWRmZTBlZjlhNGY3ZTg4Y2U3YTE1ODVkZDU5Y2Y3OGYwZWE1NzUzNWQ2YjFjZDc0NGMxZWU2MmQ3MjY1NzJmNTE0MzI=
    # 令牌过期时间 此处单位/毫秒 ，默认4小时，可在此网站生成 https://www.convertworld.com/zh-hans/time/milliseconds.html
    token-validity-in-seconds: 144000000
    # 在线用户key (1: username 2 token)
    online-key: smart.user.%s.%s
    # token 续期检查时间范围（默认30分钟，单位毫秒），在token即将过期的一段时间内用户操作了，则给用户的token续期
    detect: 1800000
    # 续期时间范围，默认1小时，单位毫秒
    renew: 3600000
    * */

    // 令牌名称
    private String header = "Authorization";

    // 令牌前缀
    private String prefix = "Bearer";

    // 令牌密钥,必须使用最少88位的Base64对该令牌进行编码
    private String secret = "ZmQ0ZGI5NjQ0MDQwY2I4MjMxY2Y3ZmI3MjdhN2ZmMjNhODViOTg1ZGE0NTBjMGM4NDA5NzYxMjdjOWMwYWRmZTBlZjlhNGY3ZTg4Y2U3YTE1ODVkZDU5Y2Y3OGYwZWE1NzUzNWQ2YjFjZDc0NGMxZWU2MmQ3MjY1NzJmNTE0MzI=";

    // 令牌过期时间,单位秒，默认1天
    private long expireTime = 24 * 60 * 60;

    // 验证码的开关,默认false
    private Boolean captchaEnable = false;

}

package com.loveverse.auth.constant;

/**
 * @author love
 * @since 2025/5/16 16:02
 */
public final class RedisKeyConstant {
    // Redis key 组成：业务前缀 + 具体功能 + 对应id
    private static final String PREFIX = "auth";

    public static final String LOGIN_ID = PREFIX + ":login:%s";

    public static final String CAPTCHA_UUID = PREFIX + ":captcha:%s";

    // 用户权限组合
    public static final String USER_PERMS = PREFIX + ":perms:%s";

    public static String build(String pattern, Object... args) {
        if (args == null) {
            return pattern;
        }
        return String.format(pattern, args);
    }
}

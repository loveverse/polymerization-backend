package com.loveverse.auth.util;

import com.loveverse.auth.bo.LoginUserBO;
import com.loveverse.auth.config.JwtProperties;
import com.loveverse.auth.entity.SysUser;
import com.loveverse.auth.exception.JwtAuthenticationException;
import com.loveverse.auth.exception.JwtTokenExpiredException;
import com.loveverse.auth.exception.JwtTokenInvalidException;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author love
 * @since 2025/4/19
 */
@Getter
@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    private final JwtProperties jwtProperties;

    /**
     * 生成token过期时间，返回Date,设置是毫秒，需要乘1000
     *
     * @return date
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + jwtProperties.getExpireTime() * 1000L);
    }

    // 生成token
    public String generateToken(LoginUserBO loginUser) {
        Map<String, Object> claims = new HashMap<>();
        SysUser user = loginUser.getUser();
        // 只存放一些基本信息
        claims.put("userId", user.getId().toString());
        claims.put("username", user.getUsername());
        claims.put("authorities", loginUser.getAuthorities());
        return Jwts.builder()
                .setClaims(claims) // 先设置claims,防止覆盖subject
                .setSubject(user.getId().toString()) // 再单独修改sub
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 设置令牌的签发时间
                .setExpiration(generateExpirationDate())     // 设置过期时间
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    // 验证token
    public Boolean validateToken(String token, LoginUserBO loginUser) {
        final String userId = extractUserId(token);
        return userId.equals(loginUser.getUser().getId().toString()) && !isTokenExpired(token);
    }

    // 从token中提取用户id
    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 提取过期时间
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 提取声明
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    // 检查token是否过期
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 提取所有声明
    public Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtTokenExpiredException("Token已过期", e);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            throw new JwtTokenInvalidException("无效的Token签名", e);
        } catch (IllegalArgumentException e) {
            throw new JwtAuthenticationException("Token不能为空", e);
        }
    }
}

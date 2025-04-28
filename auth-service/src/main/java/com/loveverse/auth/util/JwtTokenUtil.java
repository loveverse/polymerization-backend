package com.loveverse.auth.util;

import com.loveverse.auth.config.JwtProperties;
import com.loveverse.auth.dto.LoginUser;
import com.loveverse.auth.entity.SystemUser;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

/**
 * @author love
 * @since 2025/4/19
 */
@Getter
@Slf4j
@Component
public class JwtTokenUtil {
    private final JwtProperties jwtProperties;

    public JwtTokenUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 生成token过期时间，返回Date
     *
     * @return date
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + jwtProperties.getExpireTime());
    }

    /**
     * 解析token中负载信息
     *
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败：{}", token);
        }
        return claims;
    }

    // 生成token
    public String generateToken(LoginUser loginUser) {
        Map<String, Object> claims = new HashMap<>();
        SystemUser user = loginUser.getUser();
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
        claims.put("userId", user.getId());
        claims.put("authorities", authorities);
        return Jwts.builder()
                .setClaims(claims) // 先设置claims,防止覆盖subject
                .setSubject(user.getId().toString()) // 再单独修改sub
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 设置令牌的签发时间
                .setExpiration(generateExpirationDate())     // 设置过期时间
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    // 验证token
    public Boolean validateToken(String token, LoginUser loginUser) {
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
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 提取所有声明
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    // 检查token是否过期
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 解析token并验证
    public Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("Token expired", e);
            throw new RuntimeException("Token expired");
        } catch (UnsupportedJwtException e) {
            log.error("Token unsupported", e);
            throw new RuntimeException("Token unsupported");
        } catch (MalformedJwtException e) {
            log.error("Token malformed", e);
            throw new RuntimeException("Token malformed");
        } catch (SignatureException e) {
            log.error("Invalid token signature", e);
            throw new RuntimeException("Invalid token signature");
        } catch (Exception e) {
            log.error("Invalid token", e);
            throw new RuntimeException("Invalid token");
        }
    }
}

//package com.loveverse.core.util;
//
//import com.loveverse.fast.common.constant.JwtConstant;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author love
// * @since 2025/4/17
// */
//public class JwtUtil {
//    private static final String SECRET = "";
//    private static final long EXPIRATION = 86400L;
//
//    public static String generateToken(Map<String, Object> claims) {
//        return Jwts.builder()
//                .setClaims(claims) // payload 载荷
//                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.EXPIRATION)) // 设置失效时间
//                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(JwtConstant.SECRET_KEY))) // 签名
//                .compact();
//    }
//
//    public static Claims parseToken(String token) {
//        Claims claims = null;
//        try {
//            claims = Jwts.parserBuilder().setSigningKey(Decoders.BASE64.decode(JwtConstant.SECRET_KEY))
//                    .build().parseClaimsJws(token).getBody();
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//        return claims;
//    }
//
//    public static String refreshToken(String token) {
//        Claims claims = parseToken(token);
//        claims.put(JwtConstant.SECRET_KEY, new Date());
//        return generateToken(claims);
//    }
//
//}

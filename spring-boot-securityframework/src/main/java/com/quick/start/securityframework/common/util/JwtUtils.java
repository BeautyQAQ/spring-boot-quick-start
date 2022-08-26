package com.quick.start.securityframework.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 创建token
     *
     * @param username 用户名
     * @return token
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secret)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();

    }

    /**
     * 从token中获取用户名
     *
     * @param token token
     * @return 用户名
     */
    public String getUserNameFromToken(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 是否已过期
     *
     * @param token token
     * @return 是否已过期
     */
    public boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}

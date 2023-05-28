package com.jsrdxzw.springsecurityawesome.utils;

import com.jsrdxzw.springsecurityawesome.constants.ApplicationConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author xuzhiwei
 * @date 2023/5/23 21:30
 */
@Component
public class JwtTokenUtil {
    public String generateToken(UserDetails userDetails) {
        SecretKey secretKey = Keys.hmacShaKeyFor(ApplicationConstants.JWT_KEY.getBytes());
        Date now = new Date();
        return Jwts.builder()
                .setIssuer("Eazy Bank")
                .setSubject("JWT Token")
                .claim("username", userDetails.getUsername())
                .claim("authorities", populateAuthorities(userDetails.getAuthorities()))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 30000000))
                .signWith(secretKey)
                .compact();
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
    }
}

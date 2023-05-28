package com.jsrdxzw.springsecurityawesome.filter;

import com.jsrdxzw.springsecurityawesome.constants.ApplicationConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author xuzhiwei
 * @date 2023/5/21 21:54
 */
public class JWTTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(AUTHORIZATION);
        if (jwt != null) {
            try {
                SecretKey secretKey = Keys.hmacShaKeyFor(ApplicationConstants.JWT_KEY.getBytes());
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build().parseClaimsJws(jwt)
                        .getBody();
                String username = claims.get("username").toString();
                String authorities = claims.get("authorities").toString();
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username, null, parseAuthorities(authorities));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                throw new BadCredentialsException("Invalid token received!");
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/user");
    }

    private Collection<? extends GrantedAuthority> parseAuthorities(String authorities) {
        return Arrays.stream(authorities.split(",")).map(SimpleGrantedAuthority::new).toList();
    }
}

package com.jsrdxzw.springsecurityawesome.controller;

import cn.hutool.jwt.JWT;
import com.jsrdxzw.springsecurityawesome.entity.SignInReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

/**
 * @author xuzhiwei
 * @date 2023/5/13 11:32
 */
@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody SignInReq req) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(req.username(), req.password());
        authenticationManager.authenticate(authenticationToken);
        return JWT.create()
                .setPayload("username", req.username())
                .setKey("jsrdxzw".getBytes(StandardCharsets.UTF_8))
                .sign();
    }
}

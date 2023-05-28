package com.jsrdxzw.springsecurityawesome.controller;

import com.jsrdxzw.springsecurityawesome.entity.SignInReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuzhiwei
 * @date 2023/5/13 11:32
 */
@RestController
@RequestMapping("/user")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody SignInReq req) {
        return "hello";
    }
}

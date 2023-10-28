package com.example.design.controller;

import com.example.design.pojo.UserInfo;
import com.example.design.service.login.LoginAdapter;
import com.example.design.service.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @createTime 2023/10/28 15:45
 * @description
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginAdapter loginAdapter;

    @GetMapping("/gitee")
    public String giteeLogin(String code, String state) {
        return loginAdapter.loginByGitee(code, state);
    }

    @PostMapping("/login")
    public String login(String account, String password) {
        return userService.login(account, password);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        return userService.register(userInfo);
    }
}

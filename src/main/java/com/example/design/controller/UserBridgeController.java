package com.example.design.controller;

import com.example.design.pojo.UserInfo;
import com.example.design.service.UserBridgeService;
import com.example.design.service.login.LoginAdapter;
import com.example.design.service.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yanghao
 * @createTime 2023/10/28 15:45
 * @description
 */
@RestController
@RequestMapping("/bridge")
public class UserBridgeController {
    @Autowired
    private UserBridgeService userBridgeService;


    @GetMapping("/gitee")
    public String giteeLogin(HttpServletRequest request) {
        return userBridgeService.login3rd(request, "gitee");
    }

    @PostMapping("/login")
    public String login(String account, String password) {
        return userBridgeService.login(account, password);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        return userBridgeService.register(userInfo);
    }
}

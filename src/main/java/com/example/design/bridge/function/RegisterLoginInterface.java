package com.example.design.bridge.function;

import com.example.design.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yanghao
 * @createTime 2023/11/19 12:27
 * @description
 */

public interface RegisterLoginInterface {

    public String login(String account,String password);

    public String register(UserInfo userInfo);

    public boolean  checkUserExists(String userName);

    public String login3rd(HttpServletRequest request);
}

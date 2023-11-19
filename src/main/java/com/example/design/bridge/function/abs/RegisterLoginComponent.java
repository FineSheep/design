package com.example.design.bridge.function.abs;

import com.example.design.bridge.function.RegisterLoginInterface;
import com.example.design.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yanghao
 * @createTime 2023/11/19 12:38
 * @description
 */

public class RegisterLoginComponent extends AbstractRegisterLoginComponent {
    public RegisterLoginComponent(RegisterLoginInterface registerLoginInterface) {
        super(registerLoginInterface);
    }

    @Override
    public String login(String account, String password) {

        return functionInterface.login(account, password);

    }

    @Override
    public String register(UserInfo userInfo) {
        return functionInterface.register(userInfo);
    }

    @Override
    public boolean checkUserExists(String userName) {
        return functionInterface.checkUserExists(userName);
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        return functionInterface.login3rd(request);
    }
}

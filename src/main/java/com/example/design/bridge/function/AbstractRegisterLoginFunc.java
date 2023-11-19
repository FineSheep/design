package com.example.design.bridge.function;

import com.example.design.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 抽象层  消除冗余代码
 *
 * @author yanghao
 * @createTime 2023/11/19 13:26
 * @description
 */

public abstract class AbstractRegisterLoginFunc implements RegisterLoginInterface {
    @Override
    public String login(String account, String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String register(UserInfo userInfo) {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean checkUserExists(String userName) {
        throw new UnsupportedOperationException();

    }

    @Override
    public String login3rd(HttpServletRequest request) {
        throw new UnsupportedOperationException();

    }
}

package com.example.design.bridge.function;

import com.example.design.pojo.UserInfo;
import com.example.design.repo.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 抽象层  消除冗余代码
 *
 * @author yanghao
 * @createTime 2023/11/19 13:26
 * @description
 */

public abstract class AbstractRegisterLoginFunc implements RegisterLoginInterface {
    /**
     * 通用登录方法 新增 userRepository参数
     *
     * @param account
     * @param password
     * @param userRepository
     * @return
     */
    protected String commonLogin(String account, String password, UserRepository userRepository) {
        UserInfo user = userRepository.findByUserNameAndUserPassword(account, password);
        if (user == null) {
            return "ERROR";
        }
        return "success";
    }

    /**
     * 通用注册方法 新增 userRepository参数
     *
     * @param userInfo
     * @param userRepository
     * @return
     */
    protected String commonRegister(UserInfo userInfo, UserRepository userRepository) {
        if (checkUserExists(userInfo.getUserName())) {
            throw new RuntimeException("already register");
        }
        userInfo.setCreateDate(new Date());
        userRepository.save(userInfo);
        return "Success";
    }

    /**
     * 通用检查
     * @param userName
     * @param userRepository
     * @return
     */
    protected boolean commonCheckUser(String userName,UserRepository userRepository){
        UserInfo userInfo = userRepository.findByUserName(userName);
        if (userInfo == null) {
            return false;
        }
        return true;
    }

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

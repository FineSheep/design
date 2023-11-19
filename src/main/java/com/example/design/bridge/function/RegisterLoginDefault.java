package com.example.design.bridge.function;

import com.example.design.pojo.UserInfo;
import com.example.design.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author yanghao
 * @createTime 2023/11/19 12:33
 * @description
 */
@Component
public class RegisterLoginDefault extends AbstractRegisterLoginFunc implements RegisterLoginInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String account, String password) {
        UserInfo user = userRepository.findByUserNameAndUserPassword(account, password);
        if (user == null) {
            return "ERROR";
        }
        return "success";
    }

    @Override
    public String register(UserInfo userInfo) {
        if (checkUserExists(userInfo.getUserName())) {
            throw new RuntimeException("already register");
        }
        userInfo.setCreateDate(new Date());
        userRepository.save(userInfo);
        return "Success";
    }

    @Override
    public boolean checkUserExists(String userName) {
        UserInfo userInfo = userRepository.findByUserName(userName);
        if (userInfo == null) {
            return false;
        }
        return true;
    }



}

package com.example.design.service.login;

import com.example.design.pojo.UserInfo;
import com.example.design.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author yanghao
 * @createTime 2023/10/28 15:46
 * @description
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String login(String account, String password) {
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
        if (userInfo == null) {
            return "账户为空";
        }
        return "success";
    }

    public String register(UserInfo userInfo) {
        if (checkUserExists(userInfo.getUserName())){
            throw new RuntimeException("already register");
        }
        userInfo.setCreateDate(new Date());
        userRepository.save(userInfo);
        return "Success";
    }

    public boolean checkUserExists(String userName) {
        UserInfo userInfo = userRepository.findByUserName(userName);
        if (userInfo == null) {
            return false;
        }
        return true;
    }

}

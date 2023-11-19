package com.example.design.bridge.function;

import com.example.design.bridge.RegisterLoginComponentFactory;
import com.example.design.pojo.UserInfo;
import com.example.design.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

    /**
     * 工厂注册
     */
    @PostConstruct
    private void innitFuncMap() {
        RegisterLoginComponentFactory.funcMap.put("default", this);
    }

    @Override
    public String login(String account, String password) {
        return super.commonLogin(account, password, userRepository);
    }

    @Override
    public String register(UserInfo userInfo) {
        return super.commonRegister(userInfo, userRepository);
    }

    @Override
    public boolean checkUserExists(String userName) {
        return super.commonCheckUser(userName, userRepository);
    }


}

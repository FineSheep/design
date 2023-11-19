package com.example.design.service;

import com.example.design.bridge.RegisterLoginComponentFactory;
import com.example.design.bridge.function.RegisterLoginDefault;
import com.example.design.bridge.function.abs.AbstractRegisterLoginComponent;
import com.example.design.bridge.function.abs.RegisterLoginComponent;
import com.example.design.pojo.UserInfo;
import com.example.design.repo.UserRepository;
import javafx.print.PaperSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author yanghao
 * @createTime 2023/10/28 15:46
 * @description
 */
@Service
public class UserBridgeService {


    public String login(String account, String password) {
        AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent("default");
        return component.login(account, password);
    }

    public String register(UserInfo userInfo) {
        AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent("default");
        return component.register(userInfo);
    }

    public String login3rd(HttpServletRequest request, String type) {
        AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent("type");
        return component.login3rd(request);
    }

}

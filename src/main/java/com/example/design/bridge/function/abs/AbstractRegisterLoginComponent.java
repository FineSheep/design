package com.example.design.bridge.function.abs;

import com.example.design.bridge.function.RegisterLoginInterface;
import com.example.design.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yanghao
 * @createTime 2023/11/19 12:36
 * @description
 */

public abstract class AbstractRegisterLoginComponent  {

    protected RegisterLoginInterface functionInterface;

    public AbstractRegisterLoginComponent(RegisterLoginInterface registerLoginInterface){

        this.functionInterface =  registerLoginInterface;
    }

    protected  final void validate(RegisterLoginInterface registerLoginInterface){
        if(!(functionInterface instanceof  RegisterLoginInterface)){
            throw new UnsupportedOperationException("unknown register/login");
        }
    }
    public abstract String login(String account,String password);

    public abstract String register(UserInfo userInfo);

    public abstract boolean  checkUserExists(String userName);

    public abstract String login3rd(HttpServletRequest request);
}

package com.example.design.service.login;

/**
 * 对象适配器，复用userService，实现第三方登录
 * @author yanghao
 * @createTime 2023/10/28 17:08
 * @description
 */

public class LoginAdapter extends UserService implements LoginTarget{
    /**
     * gitee 登录
     *
     * @param code
     * @param state
     * @return
     */
    @Override
    public String loginByGitee(String code, String state) {
        return null;
    }

    /**
     * 微信登录
     *
     * @param param
     */
    @Override
    public void loginByWeixin(String... param) {

    }

    /**
     * qq登录
     *
     * @param param
     */
    @Override
    public void loginByQQ(String... param) {

    }
}

package com.example.design.service.login;

/**
 * @author yanghao
 * @createTime 2023/10/28 17:12
 * @description
 */
public interface LoginTarget {

    /**
     * gitee 登录
     *
     * @param code
     * @param state
     * @return
     */
    String loginByGitee(String code, String state);

    /**
     * 微信登录
     * @param param
     */
    void loginByWeixin(String... param);

    /**
     * qq登录
     * @param param
     */
    void loginByQQ(String... param);
}

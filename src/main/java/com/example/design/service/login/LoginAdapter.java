package com.example.design.service.login;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.example.design.pojo.UserInfo;
import com.example.design.ytils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * 对象适配器，复用userService，实现第三方登录
 *
 * @author yanghao
 * @createTime 2023/10/28 17:08
 * @description
 */
@Component
public class LoginAdapter extends UserService implements LoginTarget {

    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;

    /**
     * gitee 登录
     *
     * @param code
     * @param state
     * @return
     */
    @Override
    public String loginByGitee(String code, String state) {
        if (!giteeState.equals(state)) {
            throw new UnsupportedOperationException("invalid state");
        }
        String tokenUrl = giteeTokenUrl.concat(code);
        JSONObject response = HttpClientUtils.execute(tokenUrl, HttpMethod.POST);
        String token = String.valueOf(response.get("access_token"));

        String userUrl = giteeUserUrl.concat(token);
        JSONObject userInfoResponse = HttpClientUtils.execute(userUrl, HttpMethod.GET);
        String name = giteeUserPrefix.concat(String.valueOf(userInfoResponse.get("name")));
        String password = name;

        return autoRegisterAndLogin(name, password);
    }

    private String autoRegisterAndLogin(String name, String password) {
        if (super.checkUserExists(name)) {
            return super.login(name, password);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(name);
        userInfo.setUserPassword(password);
        userInfo.setCreateDate(new Date());
        super.register(userInfo);

        return super.login(name, password);
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

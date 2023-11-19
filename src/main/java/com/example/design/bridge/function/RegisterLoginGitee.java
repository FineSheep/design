package com.example.design.bridge.function;

import com.alibaba.fastjson.JSONObject;
import com.example.design.pojo.UserInfo;
import com.example.design.repo.UserRepository;
import com.example.design.ytils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author yanghao
 * @createTime 2023/11/19 12:33
 * @description
 */
@Component
public class RegisterLoginGitee implements RegisterLoginInterface {

    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;
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

    @Override
    public String login3rd(HttpServletRequest request) {

        String code = request.getParameter("code");
        String state = request.getParameter("state");
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
        if (checkUserExists(name)) {
            return login(name, password);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(name);
        userInfo.setUserPassword(password);
        userInfo.setCreateDate(new Date());
        register(userInfo);

        return login(name, password);
    }
}

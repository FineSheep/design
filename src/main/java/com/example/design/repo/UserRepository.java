package com.example.design.repo;

import com.example.design.pojo.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author yanghao
 * @createTime 2023/10/28 15:46
 * @description
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByUserName(String userName);

    UserInfo findByUserNameAndUserPassword(String account, String password);
}

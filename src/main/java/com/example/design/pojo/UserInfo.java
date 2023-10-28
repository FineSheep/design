package com.example.design.pojo;

import lombok.Data;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.util.Date;

/**
 * @author yanghao
 * @createTime 2023/10/28 15:46
 * @description
 */
@Data
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String userPassword;
    @Column(nullable = false)
    private Date createDate;
    @Column
    private String userEmail;
}

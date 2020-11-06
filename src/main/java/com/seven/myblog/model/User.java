package com.seven.myblog.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class User {
    private Long id;
    private String avatar;
    private Date createTime;
    private String email;
    private String nickname;
    private String password;
    private Integer type;
    private Date updateTime;
    private String username;
    //表关系
    private List<Blog> blogs = new ArrayList<>();
}
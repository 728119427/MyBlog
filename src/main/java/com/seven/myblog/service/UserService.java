package com.seven.myblog.service;

import com.seven.myblog.model.User;

public interface UserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);
}

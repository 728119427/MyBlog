package com.seven.myblog.service.impl;

import com.seven.myblog.mapper.UserMapper;
import com.seven.myblog.model.User;
import com.seven.myblog.model.UserExample;
import com.seven.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()==0){
            return null;
        }else {
            return users.get(0);
        }
    }
}

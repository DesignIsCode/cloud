package org.zzq.oauth.core;

import org.zzq.entity.User;
import org.zzq.oauth.mapper.UserMapper;
import org.zzq.oauth.service.UserService;

import java.util.concurrent.Callable;

public class UserCallable implements Callable<User> {
//    private User user;
    private String username;
    private String password;
    private String remark;
    private UserMapper userMapper;
    private UserService userService;

//    public UserCallable(User user, UserMapper userMapper) {
//        this.user = user;
//        this.userMapper = userMapper;
//    }

    public UserCallable(String username,String password,String remark,UserService userService){
        this.username = username;
        this.password = password;
        this.remark = remark;
        this.userService = userService;
    }

    @Override
    public User call() throws Exception {
//        userMapper.createUser(user);
        userService.createUser(username,password,remark);
        return null;
    }
}

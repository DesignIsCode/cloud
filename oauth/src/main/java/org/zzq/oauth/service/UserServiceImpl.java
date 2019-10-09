package org.zzq.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zzq.entity.User;
import org.zzq.oauth.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void createUser(String username, String password, String remark) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRemark(remark);
        int result = userMapper.createUser(user);
        System.out.println(result);
        System.out.println(user.getId());
    }
}

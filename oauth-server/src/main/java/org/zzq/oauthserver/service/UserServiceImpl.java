package org.zzq.oauthserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.zzq.entity.User;
import org.zzq.oauthserver.mapper.UserMapper;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService{

//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void createUser(String username, String password, String remark) {
        long startTime = System.currentTimeMillis();
        User user = new User();
        user.setUsername(username);
        //user.setPassword(passwordEncoder.encode(password));
        user.setRemark(remark);

        int result = userMapper.createUser(user);
        System.out.println(result);
        System.out.println(user.getId());

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        if (endTime - startTime > 2000){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}

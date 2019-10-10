package org.zzq.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.zzq.entity.User;
import org.zzq.oauth.core.MainThreadPool;
import org.zzq.oauth.core.UserCallable;
import org.zzq.oauth.mapper.UserMapper;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void createUser(String username, String password, String remark) {
        long startTime = System.currentTimeMillis();
        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
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

        //利用线程池
//        UserCallable userCallable = new UserCallable(user,userMapper);
//        Future<User> future = MainThreadPool.executor.submit(userCallable);
//        //userService.createUser(username,password,remark);
//        try {
//            user = future.get(2, TimeUnit.SECONDS);
//            System.out.println(user);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            System.out.println("aaa");
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            System.out.println("超时了");
//            e.printStackTrace();
//        }
    }
}
